package de.tum.in.dbpra;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Date;
import java.util.Iterator;
import de.tum.in.dbpra.model.bean.BookingBean;
import de.tum.in.dbpra.model.bean.LuggageBean;
import de.tum.in.dbpra.model.bean.PersonBean;

import de.tum.in.dbpra.model.dao.AbstractDAO;
import de.tum.in.dbpra.model.dao.BookingDAO;
import de.tum.in.dbpra.model.dao.LuggageDAO;


import java.io.IOException;


@SuppressWarnings("serial")
public class BookingOverviewServlet extends HttpServlet {

		//(key,value) = (hashed checkinworker, his connection)
		public LinkedHashMap<String, Connection> connections;
		public LinkedHashMap<String, Date> connectionTimestamps;
		
		@Override
	public void init(){
			
		connections = new LinkedHashMap<String, Connection>();
		connectionTimestamps = new LinkedHashMap<String, Date>();
			/*
			try{
			
		con = getConnection();
		con.setAutoCommit(false);
		}catch(SQLException e){
			
		}
		*/
	}
	

		
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//close old connections every time somebody does a post
		closeConnections();
		
PersonBean checkinworker = Authenticator.authenticate(request);
		
		if(checkinworker != null){
			//checkinworker already logged in! 
			request.setAttribute("checkinworker", checkinworker);
			
			Connection con  = getConnectionForCheckinWorker(checkinworker);
			
				
    	try{
			
    		BookingDAO bookingDao = new BookingDAO(con);
    		LuggageDAO luggageDao = new LuggageDAO(con);

			BookingBean booking = bookingDao.getBookingById(Integer.parseInt(request.getParameter("bookingid")));
    		
			if(request.getParameter("deleteLuggage") != null){
				int luggageId = Integer.parseInt(request.getParameter("deleteLuggage"));
				for(int i=0; i<booking.getLuggageList().size(); i++){
					if(luggageId == booking.getLuggageList().get(i).getId()){
						luggageDao.deleteLuggageById(booking.getLuggageList().get(i));						
						break;
					}
				}
			}
			
			
			
			
			booking = bookingDao.getBookingById(Integer.parseInt(request.getParameter("bookingid")));

			request.setAttribute("booking", booking);
    		
	}catch(Throwable e){
		request.setAttribute("error", e.getMessage());

	}
    	
    	
    	if(request.getParameter("rollback") != null){
			BookingDAO bookingDao = new BookingDAO(con);
			BookingBean booking = new BookingBean();
    		try{
    			con.rollback();
        		booking = bookingDao.getBookingById(Integer.parseInt(request.getParameter("bookingid")));
    		}catch(Throwable e){
    			request.setAttribute("error", e.getMessage());

    		}
    		
    		
			request.setAttribute("booking", booking);
    		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookingOverview.jsp");
    		dispatcher.forward(request, response);
		}

    	else if(request.getParameter("edit") != null){
    	    	try{
    		LuggageDAO luggageDao = new LuggageDAO(con);
    		LuggageBean luggageItem = new LuggageBean();
    		luggageItem.setId(Integer.parseInt(request.getParameter("edit")));
    		luggageDao.getLuggageById(luggageItem);
    		
    		
    		BookingDAO bookingDao = new BookingDAO(con);
    		BookingBean booking = new BookingBean();
    		booking.setId(luggageItem.getBookingId());
    		booking=bookingDao.getBookingById(booking.getId());
    		
			request.setAttribute("luggage", luggageItem);
			request.setAttribute("booking", booking);

    		}catch(Throwable e){
    			request.setAttribute("error", e.getMessage());
    		}
    	    	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/specifyLuggageItem.jsp");
    		dispatcher.forward(request, response);
    	}

    
    
    else if(request.getParameter("calculateCostButton") != null || request.getParameter("addLuggage") != null){
    	try{
    		
    LuggageDAO luggageDao = new LuggageDAO(con);
	LuggageBean luggageItem = new LuggageBean();
	
	
	luggageItem.setId(Integer.parseInt(request.getParameter("luggageId")));

	
	
	//to fill the booking-id
	luggageDao.getLuggageById(luggageItem);
		
	luggageItem.setWeight(Integer.parseInt(request.getParameter("weight")));
	luggageItem.setWidth(Integer.parseInt(request.getParameter("width")));
	luggageItem.setHeight(Integer.parseInt(request.getParameter("height")));
	luggageItem.setLength(Integer.parseInt(request.getParameter("length")));

	
	BookingDAO bookingDao = new BookingDAO(con);
	BookingBean booking = new BookingBean();
	booking.setId(luggageItem.getBookingId());
	booking=bookingDao.getBookingById(booking.getId());
	
	
	
	luggageItem.setAdditionalPrice(roundBigDecimal(calculateAdditionalCost(luggageItem).multiply(booking.getCurrency().getPriceInDollar())));

	
	luggageDao.updateLuggageById(luggageItem);
	request.setAttribute("luggage", luggageItem);
	
	booking=bookingDao.getBookingById(booking.getId());

	request.setAttribute("booking", booking);
	}catch(Throwable e){
		request.setAttribute("error", e.getMessage());
	}
    	
    	//pressed calculate cost button
   	 if(request.getParameter("calculateCostButton") != null){
	RequestDispatcher dispatcher = request.getRequestDispatcher("/specifyLuggageItem.jsp");
	dispatcher.forward(request, response);
	 }
   	 
   	 //pressed "OK" button
	 else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookingOverview.jsp");
			dispatcher.forward(request, response);	 
		}
}    
    
    
    
    else if(request.getParameter("addNewLuggage") != null){
    	
try{
    		
	LuggageBean luggageItem = new LuggageBean();
	luggageItem.setBookingId(Integer.parseInt(request.getParameter("bookingid")));
	//set some default values
	luggageItem.setWeight(5000);
	luggageItem.setHeight(50);
	luggageItem.setWidth(50);
	luggageItem.setLength(50);
	
	BookingDAO bookingDao = new BookingDAO(con);
	BookingBean booking = new BookingBean();
	booking.setId(luggageItem.getBookingId());
	booking=bookingDao.getBookingById(booking.getId());
	
	luggageItem.setAdditionalPrice(roundBigDecimal(calculateAdditionalCost(luggageItem).multiply(booking.getCurrency().getPriceInDollar())));
	luggageItem.setRegisteredAtBooking(false);
	
	LuggageDAO luggageDao = new LuggageDAO(con);
	luggageDao.addNewLuggageItem(luggageItem);
	
	luggageDao.getLuggageById(luggageItem);
	
	request.setAttribute("luggage", luggageItem);
	request.setAttribute("booking", booking);
	
	
	}catch(Throwable e){
		request.setAttribute("error", e.getMessage());
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher("/specifyLuggageItem.jsp");
	dispatcher.forward(request, response);
    	
    }
    
    
    else if(request.getParameter("commit") != null){
    	try{
    		BookingDAO bookingDao = new BookingDAO(con);
    		BookingBean booking = new BookingBean();
    		booking.setId(Integer.parseInt(request.getParameter("bookingid")));
    		booking=bookingDao.getBookingById(booking.getId());
    		
    		booking.setCheckedInOn(new Date());
    		
    		//TODO
    		//set the checkedInOn timestamp
    		bookingDao.finishCheckIn(booking);
    		
    		con.commit();
    		con.close();
    		connections.remove(generateCheckInWorkerHash(checkinworker));
    		connectionTimestamps.remove(generateCheckInWorkerHash(checkinworker));
	}catch(Throwable e){
		request.setAttribute("error", e.getMessage());
	}
    	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/checkInConfirmation.jsp");
	dispatcher.forward(request, response);
}
    
    else{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bookingOverview.jsp");
		dispatcher.forward(request, response);
    }
		
    
    
		}		
		else{
			//checkinworker not logged in (no cookie found). 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			}
    
		
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
		//close old connections every time somebody does a get
		closeConnections();

	RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
	dispatcher.forward(request, response);
    

		
    }
    
    
    /*
     * sets the additional cost for a certain luggage item
     */
    private BigDecimal calculateAdditionalCost(LuggageBean luggageItem){
    	BigDecimal additionalCost = null;
    	
    	if(luggageItem.getRegisteredAtBooking()){
    		try{
    		LuggageDAO luggageDao = new LuggageDAO();
    		LuggageBean oldLuggageItem = luggageDao.getOldLuggageItem(luggageItem);
    		
    		BigDecimal difference = costFunction(luggageItem).subtract(costFunction(oldLuggageItem));
    		if(difference.compareTo(BigDecimal.ZERO) > 0){
    			additionalCost = difference.add(oldLuggageItem.getAdditionalPrice()).add(new BigDecimal(7));
    		}
    		else{
    			additionalCost = BigDecimal.ZERO;
    		}
    		}catch(Throwable e){
    			return null;
    		}
    	}
    	else{
    		additionalCost=costFunction(luggageItem);
    	}
    	return additionalCost;
    }
    
    
    /*
     * calculates the basic additional price based on weight, width, length and height of the luggage item
     */
    private BigDecimal costFunction(LuggageBean luggage){
    	BigDecimal price = new BigDecimal(0);
    	
    	price = new BigDecimal(((luggage.getWeight()/1000.0)+luggage.getWidth()+luggage.getHeight()+luggage.getLength())/100.0);
    	
    	return roundBigDecimal(price);
    }
    
    
    /*
     * rounds a price to two decimal places
     */
    private BigDecimal roundBigDecimal(BigDecimal price){
    	return price.setScale(2, BigDecimal.ROUND_UP);
    }
    

    private String generateCheckInWorkerHash(PersonBean checkinworker){
    	return checkinworker.getEmail()+checkinworker.getPassword();
    }
    
    private Connection getConnectionForCheckinWorker(PersonBean checkinworker){
    	//get the connection of this checkinworker. Its either already stored in the connections hashmap or a new connection has to be created
		String checkInWorkerHash = generateCheckInWorkerHash(checkinworker);
		Connection con = connections.get(checkInWorkerHash);
		if(con == null){
			//not yet in the hashMap
			try{
				AbstractDAO abstractDao = new AbstractDAO();
				con = abstractDao.getConnection();
				con.setAutoCommit(false);
				}catch(SQLException e){
					
				}
			connections.put(checkInWorkerHash, con);
			connectionTimestamps.put(checkInWorkerHash, new Date());
		}
    	
    	return con;
    }
    
    private void closeConnections(){
    	Iterator<String> iterator = connectionTimestamps.keySet().iterator();
    	while(iterator.hasNext()){
    		String key = iterator.next();
    		Date timestamp = connectionTimestamps.get(key);
    		if((new Date().getTime() - timestamp.getTime())/(1000*60) > 5){
    			//it is older than 5 minutes -> close and delete connection
    			Connection con = connections.get(key);
    			try{
        		con.rollback();
        		con.close();
    			}catch(Throwable e){
    				
    			}
        		connections.remove(key);
        		connectionTimestamps.remove(key);
    		}
    	}
    }
    /*
    protected Connection getConnection() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		};
        return DriverManager.getConnection("jdbc:postgresql://localhost/airporTUM_webapp", "postgres", "p");
    }
    */
}
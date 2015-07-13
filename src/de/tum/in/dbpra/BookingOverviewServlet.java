package de.tum.in.dbpra;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.math.BigDecimal;

import de.tum.in.dbpra.model.bean.BookingBean;
import de.tum.in.dbpra.model.bean.LuggageBean;
import de.tum.in.dbpra.model.bean.PersonBean;


import de.tum.in.dbpra.model.dao.PersonDAO;
import de.tum.in.dbpra.model.dao.BookingDAO;
import de.tum.in.dbpra.model.dao.LuggageDAO;


import java.io.IOException;


@SuppressWarnings("serial")
public class BookingOverviewServlet extends HttpServlet {


		public Connection con;
		
		@Override
	public void init(){
		try{
		con = getConnection();
		con.setAutoCommit(false);
		}catch(SQLException e){
			
		}
	}
	

		public Connection getCon(){
			return con;
		}
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

PersonBean checkinworker = Authenticator.authenticate(request);
		
		if(checkinworker != null){
			//checkinworker already logged in! Thus, everything is fine.
			request.setAttribute("checkinworker", checkinworker);


    
		
		
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
			
			if(request.getParameter("rollback") != null){
				con.rollback();
			}
			
			
			booking = bookingDao.getBookingById(Integer.parseInt(request.getParameter("bookingid")));

			request.setAttribute("booking", booking);
    		
	}catch(Throwable e){
		request.setAttribute("error", e.getMessage());

	}
    	

    if(request.getParameter("edit") != null){
    	    	try{
    		LuggageDAO luggageDao = new LuggageDAO(con);
    		LuggageBean luggageItem = new LuggageBean();
    		luggageItem.setId(Integer.parseInt(request.getParameter("edit")));
    		luggageDao.getLuggageById(luggageItem);
			request.setAttribute("luggage", luggageItem);


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
	luggageItem.setAdditionalPrice(calculateAdditionalCost(luggageItem));
	luggageDao.updateLuggageById(luggageItem);
	request.setAttribute("luggage", luggageItem);
	
	BookingDAO bookingDao = new BookingDAO(con);
	BookingBean booking = new BookingBean();
	booking.setId(luggageItem.getBookingId());
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
	luggageItem.setHeight(500);
	luggageItem.setWidth(500);
	luggageItem.setLength(500);
	luggageItem.setAdditionalPrice(calculateAdditionalCost(luggageItem));
	
	
	LuggageDAO luggageDao = new LuggageDAO(con);
	luggageDao.addNewLuggageItem(luggageItem);
	
	luggageDao.getLuggageById(luggageItem);
	
	request.setAttribute("luggage", luggageItem);

	
	
	}catch(Throwable e){
		request.setAttribute("error", e.getMessage());
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher("/specifyLuggageItem.jsp");
	dispatcher.forward(request, response);
    	
    }
    
    
    else if(request.getParameter("commit") != null){
    	try{
    		con.commit();
    		
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
    	
    	

	RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
	dispatcher.forward(request, response);
    

		
    }
    
    
    private BigDecimal calculateAdditionalCost(LuggageBean luggage){
    	//TODO!!!
    	return new BigDecimal(199.00);
    }
    
    
    protected Connection getConnection() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		};
        return DriverManager.getConnection("jdbc:postgresql://localhost/airporTUM_webapp", "postgres", "p");
    }
}
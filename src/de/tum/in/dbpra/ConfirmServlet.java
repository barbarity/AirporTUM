package de.tum.in.dbpra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.BookingBean;
import de.tum.in.dbpra.model.bean.CityListBean;
import de.tum.in.dbpra.model.bean.CurrencyBean;
import de.tum.in.dbpra.model.bean.CurrencyListBean;
import de.tum.in.dbpra.model.bean.FlightBean;
import de.tum.in.dbpra.model.bean.FlightSegmentTicketBean;
import de.tum.in.dbpra.model.bean.PersonBean;
import de.tum.in.dbpra.model.dao.AbstractDAO;
import de.tum.in.dbpra.model.dao.BookingDAO;
import de.tum.in.dbpra.model.dao.CityDAO;
import de.tum.in.dbpra.model.dao.CurrencyDAO;
import de.tum.in.dbpra.model.dao.FlightDAO;
import de.tum.in.dbpra.model.dao.FlightSegmentTicketDAO;
import de.tum.in.dbpra.model.dao.PersonDAO;
import de.tum.in.dbpra.model.bean.AirlineListBean;
import de.tum.in.dbpra.model.dao.AirlineDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;


@SuppressWarnings("serial")
public class ConfirmServlet extends HttpServlet {
	
	public LinkedHashMap<String, Connection> connections;
	public LinkedHashMap<String, Date> connectionTimestamps;
	
	@Override
	public void init(){
		
		connections = new LinkedHashMap<String, Connection>();
		connectionTimestamps = new LinkedHashMap<String, Date>();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		try {
			PersonDAO personDAO = new PersonDAO();
			FlightDAO flightDAO = new FlightDAO();
			
			ArrayList<PersonBean> people = new ArrayList<PersonBean>();
			
			//String className = request.getParameter("className");
			String className = "economy";
			//String currencyCode = request.getParameter("currencyCode");
			String currencyCode = "EUR";
			
			String peopleIds[] = request.getParameterValues("people");

			for (int i = 0; i < peopleIds.length; i++) {
				PersonBean person = new PersonBean();
				person.setId(Integer.parseInt(peopleIds[i]));
				person = personDAO.getPersonById(person);
				people.add(person);
			}
			
			ArrayList<FlightBean> flights = new ArrayList<FlightBean>();
			
			String flightsIds[] = request.getParameterValues("flights");
			
			for (int i = 0; i < flightsIds.length; i++) {
				FlightBean flight = new FlightBean();
				flight.setFlightId(Integer.parseInt(flightsIds[i]));
				flight = flightDAO.getFlightById(flight);
				flights.add(flight);
			}
			
			if (request.getParameter("confirm") != null) {
				try {
					Connection connection = getConnectionForBooking(people.get(0));
					BookingDAO bookingDAO = new BookingDAO(connection);
					CurrencyDAO currencyDAO = new CurrencyDAO();
					
					CurrencyBean currency = new CurrencyBean();
					currency.setCurrencyCode(currencyCode);
					
					currency = currencyDAO.getCurrencyByCurrencyCode(currency);
					
					FlightSegmentTicketDAO fstDAO = new FlightSegmentTicketDAO();
					
					for(PersonBean person: people) {
						BookingBean booking = new BookingBean();
						booking.setCurrency(currency);
						booking.setFlightSegmentTicketList(new ArrayList<FlightSegmentTicketBean>());
						booking.setPerson(person);
						for (FlightBean flight: flights) {
							FlightSegmentTicketBean fst = new FlightSegmentTicketBean();
							fst.setBookedFlightNumber(flight.getFlightId() + "");
							fst.setFlight(flight);
							fst.setPrice(new BigDecimal(flight.getDistance()/100));
							fst.setSeatNr((int)Math.random()*100);
							fst.setTravelClass(className);
							booking.getFlightSegmentTicketList().add(fst);
						}
						
						
						bookingDAO.addNewBooking(booking);
						
						connection.commit();
					}
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
		
    }
	
	private String generateBookingHash(PersonBean checkinworker){
    	return checkinworker.getEmail()+checkinworker.getPassword();
    }
	
	private Connection getConnectionForBooking(PersonBean person){
    	//get the connection of this checkinworker. Its either already stored in the connections hashmap or a new connection has to be created
		String personHash = generateBookingHash(person);
		Connection con = connections.get(personHash);
		if(con == null){
			//not yet in the hashMap
			try{
				AbstractDAO abstractDao = new AbstractDAO();
				con = abstractDao.getConnection();
				con.setAutoCommit(false);
				}catch(SQLException e){
					
				}
			connections.put(personHash, con);
			connectionTimestamps.put(personHash, new Date());
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
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	

		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
    }
}
package de.tum.in.dbpra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.CityListBean;
import de.tum.in.dbpra.model.bean.CurrencyListBean;
import de.tum.in.dbpra.model.bean.PersonBean;
import de.tum.in.dbpra.model.dao.AbstractDAO;
import de.tum.in.dbpra.model.dao.CityDAO;
import de.tum.in.dbpra.model.dao.CurrencyDAO;
import de.tum.in.dbpra.model.dao.FlightDAO;
import de.tum.in.dbpra.model.dao.PersonDAO;
import de.tum.in.dbpra.model.bean.AirlineListBean;
import de.tum.in.dbpra.model.dao.AirlineDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

			String peopleIds[] = request.getParameterValues("people");

			for (int i = 0; i < peopleIds.length; i++) {
				PersonBean person = new PersonBean();
				person.setPassportId(peopleIds[i]);
				person = personDAO.getPersonById(person);
				people.add(person);
			}

			Connection connection = getConnectionForBooking(people.get(0));
			
			if (request.getParameter("confirm") != null) {
				for(PersonBean person: people) {
					
				}
			}
    	} catch (Throwable e) {
    		con.rollback();
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
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	

		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
    }
}
package de.tum.in.dbpra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.CityListBean;
import de.tum.in.dbpra.model.bean.ConnectionBean;
import de.tum.in.dbpra.model.bean.FlightBean;
import de.tum.in.dbpra.model.bean.PersonBean;
import de.tum.in.dbpra.model.dao.CityDAO;
import de.tum.in.dbpra.model.dao.FlightDAO;
import de.tum.in.dbpra.model.dao.PersonDAO;
import de.tum.in.dbpra.model.dao.PersonDAO.PersonNotFoundException;
import de.tum.in.dbpra.model.bean.AirlineListBean;
import de.tum.in.dbpra.model.dao.AirlineDAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class PayServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int peopleLeft = Integer.parseInt(request.getParameter("peopleLeft"));
			PersonDAO personDAO = new PersonDAO();
			FlightDAO flightDAO = new FlightDAO();
			ArrayList<PersonBean> people = new ArrayList<PersonBean>();
			
			// Get users if exist
			try {
				String peopleIds[] = request.getParameterValues("people");
			
				for (int i = 0; i < peopleIds.length; i++) {
					PersonBean person = new PersonBean();
					person.setId(Integer.parseInt(peopleIds[i]));
					person = personDAO.getPersonById(person);
					people.add(person);
				}
				
				
			} catch (Throwable e) {
				// It does not exist yet people
			}
			if (request.getParameter("login") != null) {
				// Login User
				String email = request.getParameter("emailLogin");
				String password = request.getParameter("passwordLogin");
				
				try {
					PersonBean newPerson = personDAO.getPerson(email, personDAO.getSha256Hash(email, password));
					
					// Success
					peopleLeft--;
					people.add(newPerson);
					
				} catch (SQLException e) {
					// TODO: Set an error message to show in the page
				}
			} else if (request.getParameter("register") != null) {
				String passportId = request.getParameter("passportId");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String repeatPassword = request.getParameter("repeatPassword");
				String title = request.getParameter("title");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String birthDate = request.getParameter("birthDate");
				String phone = request.getParameter("phone");
				String gender = request.getParameter("gender");
				String address = request.getParameter("address");
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				
				try {
					PersonBean newPerson = personDAO.addNewPerson(firstName, lastName, passportId, gender, title, address, email, phone, new Date(formatter.parse(birthDate).getTime()), repeatPassword, "salt");
					
					peopleLeft--;
					people.add(newPerson);
					
				} catch (PersonNotFoundException e) {
					// TODO: Set an error message
				}
			}
			
			ArrayList<FlightBean> flights = new ArrayList<FlightBean>();
			// FlightDAO flightDAO = new FlightDAO();
			
			String flightCodes[] = request.getParameterValues("flights");
			
			for (int i = 0; i < flightCodes.length; i++) {
				FlightBean flight = new FlightBean();
				flight.setFlightId(Integer.parseInt(flightCodes[i]));
				flight = flightDAO.getFlightById(flight);
				flights.add(flight);
			}

			// Prepare attribute connection
			ConnectionBean connectionBean = new ConnectionBean();
			
			connectionBean.setFlightList(flights);
			
			request.setAttribute("connectionBean", connectionBean);
			
			request.setAttribute("people", people);
			request.setAttribute("peopleLeft", peopleLeft);
			request.setAttribute("className", request.getParameter("className"));
			
			if (peopleLeft == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmBooking.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pay.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	try{
    	CityDAO cityDao = new CityDAO();
    	CityListBean cityList = cityDao.getCities();
    	request.setAttribute("cityList", cityList);
    	
    	
		AirlineDAO airlineDao = new AirlineDAO();
		AirlineListBean airlineList = airlineDao.getAirlines();
    	request.setAttribute("airlineList", airlineList);

    	}catch(Throwable e){
    		request.setAttribute("error", e.getMessage());

    	}
    	

		RequestDispatcher dispatcher = request.getRequestDispatcher("/searchFlight.jsp");
		dispatcher.forward(request, response);
		
    }
}
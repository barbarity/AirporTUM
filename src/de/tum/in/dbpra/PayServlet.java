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
import de.tum.in.dbpra.model.bean.AirlineListBean;
import de.tum.in.dbpra.model.dao.AirlineDAO;

import java.io.IOException;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class PayServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			int peopleLeft = Integer.parseInt(request.getParameter("peopleLeft"));
			
			if (request.getParameter("login") != null) {
				// Check User
				if (true) {
					peopleLeft--;
				} else {
					// TODO: Set an error message to show in the page
				}
			} else if (request.getParameter("register") != null) {
				// Register User
				if (true) {
					peopleLeft--;
				} else {
					// TODO: Set an error message to show in the page
				}
			}
			
			// Prepare attribute connection
			ConnectionBean connectionBean = new ConnectionBean();
			
			ArrayList<FlightBean> flights = new ArrayList<FlightBean>();
			// FlightDAO flightDAO = new FlightDAO();
			
			String flightCodes[] = request.getParameterValues("flights");
			
			for (int i = 0; i < flightCodes.length; i++) {
				FlightBean flight = new FlightBean();
				//FlightBean flight = flightDAO.getFlightById(flightCodes[i]);
				flights.add(flight);
			}
			
			connectionBean.setFlightList(flights);
			
			request.setAttribute("connectionBean", connectionBean);
			
			// Prepare attribute people
			ArrayList<PersonBean> people = new ArrayList<PersonBean>();
			// PersonDAO personDAO = new PersonDAO();
			String peopleIds[] = request.getParameterValues("people");
			
			for (int i = 0; i < peopleIds.length; i++) {
				PersonBean person = new PersonBean();
				//PersonBean person = personDAO.getPersonById(personIds[i]);
				people.add(person);
			}
			
			request.setAttribute("people", people);
			
			if (peopleLeft == 0) {
				
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pay.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Throwable e) {
			
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
package de.tum.in.dbpra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.CityListBean;
import de.tum.in.dbpra.model.bean.CityBean;
import de.tum.in.dbpra.model.dao.CityDAO;
import de.tum.in.dbpra.model.bean.AirlineListBean;
import de.tum.in.dbpra.model.bean.AirlineBean;
import de.tum.in.dbpra.model.dao.AirlineDAO;


import java.io.IOException;


@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		try{
		CityDAO cityDao = new CityDAO();
		
		CityBean departureCity = new CityBean();
		departureCity.setId(Integer.parseInt(request.getParameter("departureCity").trim()));
		departureCity=cityDao.getCityById(departureCity);
		
		CityBean arrivalCity = new CityBean();
		arrivalCity.setId(Integer.parseInt(request.getParameter("arrivalCity").trim()));
		arrivalCity=cityDao.getCityById(arrivalCity);
				
		CityListBean cityList = cityDao.getCities();
		
    	request.setAttribute("cityList", cityList);
		request.setAttribute("departureCity", departureCity);
		request.setAttribute("arrivalCity", arrivalCity);

		AirlineDAO airlineDao = new AirlineDAO();
		AirlineBean preferredAirline = new AirlineBean();
		preferredAirline.setCode(request.getParameter("preferredAirline").trim());
		preferredAirline=airlineDao.getAirlineById(preferredAirline);
		
		AirlineListBean airlineList = airlineDao.getAirlines();
		
    	request.setAttribute("airlineList", airlineList);
		request.setAttribute("preferredAirline", preferredAirline);
		
		}catch(Throwable e){
    		request.setAttribute("error", e.getMessage());

    	}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/searchFlight.jsp");
		dispatcher.forward(request, response);
		
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
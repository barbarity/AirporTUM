package de.tum.in.dbpra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.CityListBean;
import de.tum.in.dbpra.model.bean.CityBean;
import de.tum.in.dbpra.model.bean.ConnectionListBean;
import de.tum.in.dbpra.model.bean.CurrencyBean;
import de.tum.in.dbpra.model.dao.CityDAO;
import de.tum.in.dbpra.model.dao.ConnectionDAO;
import de.tum.in.dbpra.model.bean.AirlineListBean;
import de.tum.in.dbpra.model.bean.AirlineBean;
import de.tum.in.dbpra.model.dao.AirlineDAO;

import java.io.IOException;
import java.sql.Date;


@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		try {
			ConnectionDAO connectionDAO = new ConnectionDAO();
			CityDAO cityDao = new CityDAO();
			
			CityBean departureCity = new CityBean();
			departureCity.setId(Integer.parseInt(request.getParameter("departureCity").trim()));
			departureCity=cityDao.getCityById(departureCity);
			
			CityBean arrivalCity = new CityBean();
			arrivalCity.setId(Integer.parseInt(request.getParameter("arrivalCity").trim()));
			Date dateFrom = new Date(8, 6, 2015);
			Date dateTo = new Date(8, 6, 2015);
			
			CurrencyBean currency = new CurrencyBean();
			currency.setCurrencyCode("EUR");
			currency.setName("Euros");
			currency.setSymbol("€");
			
			ConnectionListBean connectionList = connectionDAO.getConnections(dateFrom, dateTo, departureCity, arrivalCity, "economy", currency);
			
			request.setAttribute("connectionList", connectionList);
		
		} catch(Throwable e){
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
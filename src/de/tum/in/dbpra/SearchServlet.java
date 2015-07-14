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
import de.tum.in.dbpra.model.dao.FlightConnection;
import de.tum.in.dbpra.model.dao.CurrencyDAO;
import de.tum.in.dbpra.model.bean.AirlineListBean;
import de.tum.in.dbpra.model.bean.AirlineBean;
import de.tum.in.dbpra.model.dao.AirlineDAO;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		try {
			// Parse information from the post request
			int people = Integer.parseInt(request.getParameter("people"));
			String className = request.getParameter("className");
			
			// Prepare information to get connections
			FlightConnection connectionDAO = new FlightConnection();
			
			SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
			
			Time dateFrom = new Time(parser.parse(request.getParameter("dateFrom")).getTime());
			Time dateTo = new Time(parser.parse(request.getParameter("dateTo")).getTime());
			dateTo.setHours(23);
			dateTo.setMinutes(59);
			dateTo.setSeconds(59);
			
			CityDAO cityDao = new CityDAO();
			
			CityBean departureCity = new CityBean();
			departureCity.setId(Integer.parseInt(request.getParameter("departureCity").trim()));
			departureCity=cityDao.getCityById(departureCity);
			
			CityBean arrivalCity = new CityBean();
			arrivalCity.setId(Integer.parseInt(request.getParameter("arrivalCity").trim()));
			arrivalCity = cityDao.getCityById(arrivalCity);
			
			CurrencyDAO currencyDAO = new CurrencyDAO();
			
			CurrencyBean currency = new CurrencyBean();
			currency.setCurrencyCode(request.getParameter("currency"));
			currency = currencyDAO.getCurrencyByCurrencyCode(currency);
			
			ConnectionListBean connectionList = connectionDAO.getConnections(dateFrom, dateTo, departureCity, arrivalCity, "economy", currency, people);
			
			request.setAttribute("connectionList", connectionList);
			request.setAttribute("people", people);
		
		} catch(Throwable e){
    		request.setAttribute("error", e.getMessage());

    	}
		// Send the response
		RequestDispatcher dispatcher = request.getRequestDispatcher("/searchFlight.jsp");
		dispatcher.forward(request, response);
		
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
    }
}
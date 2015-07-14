package de.tum.in.dbpra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tum.in.dbpra.model.bean.CityListBean;
import de.tum.in.dbpra.model.bean.CurrencyListBean;
import de.tum.in.dbpra.model.dao.CityDAO;
import de.tum.in.dbpra.model.dao.CurrencyDAO;
import de.tum.in.dbpra.model.bean.AirlineListBean;
import de.tum.in.dbpra.model.dao.AirlineDAO;

import java.io.IOException;


@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	// dateFrom DD/MM/YYYY 00:00 and dateTo DD/MM/YYYY 23:59 (Time Classes)
    	try{
    	CityDAO cityDao = new CityDAO();
    	CityListBean cityList = cityDao.getCities();
    	request.setAttribute("cityList", cityList);
    	
    	
		AirlineDAO airlineDao = new AirlineDAO();
		AirlineListBean airlineList = airlineDao.getAirlines();
    	request.setAttribute("airlineList", airlineList);
    	
    	CurrencyDAO currencyDAO = new CurrencyDAO();
    	CurrencyListBean currencyList = currencyDAO.getCurrencies();
    	request.setAttribute("currencyList", currencyList);

    	}catch(Throwable e){
    		request.setAttribute("error", e.getMessage());

    	}
    	

		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
    }
}
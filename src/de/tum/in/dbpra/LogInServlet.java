package de.tum.in.dbpra;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import de.tum.in.dbpra.model.bean.PersonBean;
import de.tum.in.dbpra.model.dao.PersonDAO;


import java.io.IOException;


@SuppressWarnings("serial")
public class LogInServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PersonBean checkinworker = null;
		try{
	    	PersonDAO personDao = new PersonDAO();
	    	checkinworker = personDao.getCheckInWorker(request.getParameter("username"), request.getParameter("password"));

	    	
	    	
	    	
	    	}catch(Throwable e){
	    		request.setAttribute("error", e.getMessage());
	    	}
		
		
		if(checkinworker != null){
			
	    	Cookie usernameCookie = new Cookie("username", checkinworker.getEmail());
	    	Cookie passwordCookie = new Cookie("password", checkinworker.getPassword());
	    	response.addCookie(usernameCookie);
	    	response.addCookie(passwordCookie);
	    	
	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/checkIn.jsp");
			dispatcher.forward(request, response);
	    	}
		
		else{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		
		dispatcher.forward(request, response);
		}
		
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
		
    }
}
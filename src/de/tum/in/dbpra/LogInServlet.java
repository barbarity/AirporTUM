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

		if(request.getParameter("logoutUser") != null){
			
			String username;
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for (int i=0; i<cookies.length; i++){
					if(cookies[i].getName().equals("username")){
						username = cookies[i].getValue();
						if(username.equals(request.getParameter("logoutUser"))){
							//delete cookie
							cookies[i].setMaxAge(0);
						}
					}
					else if(cookies[i].getName().equals("password")){
						cookies[i].setMaxAge(0);
					}
					response.addCookie(cookies[i]);
				}
			}
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			
			
		}
		else{
		
		
PersonBean checkinworker = null;
		
	//checkinworker tries to login
		try{
			
	    	PersonDAO personDao = new PersonDAO();		
	    	String email = request.getParameter("username");
	    	String passwordHashed = personDao.getSha256Hash(email, request.getParameter("password"));
	    	checkinworker = personDao.getCheckInWorker(email, passwordHashed);

	    	}catch(Throwable e){
	    		request.setAttribute("error", e.getMessage());
	    	}
		
		
		if(checkinworker != null){
			
	    	Cookie usernameCookie = new Cookie("username", checkinworker.getEmail());
	    	Cookie passwordCookie = new Cookie("password", checkinworker.getPassword());
	    	response.addCookie(usernameCookie);
	    	response.addCookie(passwordCookie);
	    	
    		request.setAttribute("checkinworker", checkinworker);

	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/checkIn.jsp");
			dispatcher.forward(request, response);
	    	}
		
		else{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
		}
		}
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	
PersonBean checkinworker = Authenticator.authenticate(request);
		
		if(checkinworker != null){
			//checkinworker already logged in! Thus, go to the checkIn page
    		request.setAttribute("checkinworker", checkinworker);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/checkIn.jsp");
			dispatcher.forward(request, response);
		}
		else{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
		}
    }
}
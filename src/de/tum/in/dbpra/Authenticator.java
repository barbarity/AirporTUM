package de.tum.in.dbpra;

import javax.servlet.http.HttpServletRequest;

import de.tum.in.dbpra.model.bean.PersonBean;
import de.tum.in.dbpra.model.dao.PersonDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;



public class Authenticator {

	
	
	
	public static PersonBean authenticate(HttpServletRequest request) {
		PersonBean checkInWorker = null;
		PersonDAO personDao = new PersonDAO();
		
		String username = "";
		String passwordHashed= "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (int i=0; i<cookies.length; i++){
				if(cookies[i].getName().equals("username")){
					username = cookies[i].getValue();
				}
				else if(cookies[i].getName().equals("password")){
					passwordHashed = cookies[i].getValue();
				}
			}
		}
		
		try{
		checkInWorker = personDao.getCheckInWorker(username, passwordHashed);
		}catch(Throwable e){
			request.setAttribute("error", e.getMessage());
		}
		
		return checkInWorker;
	}

}

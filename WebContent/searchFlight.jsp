<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="de.tum.in.dbpra.model.bean.CityBean" %>
<%@page import="de.tum.in.dbpra.model.bean.AirlineBean" %>

<jsp:useBean id="cityList" scope="request" class="de.tum.in.dbpra.model.bean.CityListBean" />
<jsp:useBean id="departureCity" scope="request" class="de.tum.in.dbpra.model.bean.CityBean" />
<jsp:useBean id="arrivalCity" scope="request" class="de.tum.in.dbpra.model.bean.CityBean" />
<jsp:useBean id="airlineList" scope="request" class="de.tum.in.dbpra.model.bean.AirlineListBean" />
<jsp:useBean id="preferredAirline" scope="request" class="de.tum.in.dbpra.model.bean.AirlineBean" />


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Orders</title>
        <link href="style.css" rel="stylesheet" media="screen">
    </head>
    <body>
    
    <% if (departureCity.getId()!=-1 && arrivalCity.getId()!=-1 && preferredAirline.getCode() != null){ %>
   Here are the connections from <%= departureCity.getName() %> to  <%= arrivalCity.getName() %> using <%= preferredAirline.getName() %>.
   <%} %>
    
     <form action="/AirportTUM/search" method="post">
	 	<h3>Search connections</h3>
	 	From 
		    <select name="departureCity" size="1">
		        <%for(int i=0;i< cityList.getCityList().size();i++){%>
    			<% CityBean city = cityList.getCityList().get(i); %>
    			<option <% if(city.getId() == departureCity.getId()){ %> selected <% } %> value="<%=city.getId()%>"><%=city.getName()%> (<%=city.getCountryName()%>)</option>
    			<%}%> 
  			</select>
  		To 
  			<select name="arrivalCity" size="1">
		        <%for(int i=0;i< cityList.getCityList().size();i++){%>
    			<% CityBean city = cityList.getCityList().get(i); %>
    			<option <% if(city.getId() == arrivalCity.getId()){ %> selected <% } %> value="<%=city.getId()%>"><%=city.getName()%> (<%=city.getCountryName()%>)</option>
    			<%}%> 
 
  			</select>
  		with airline
  			<select name="preferredAirline" size="1">
		        <%for(int i=0;i< airlineList.getAirlineList().size();i++){%>
    			<% AirlineBean airline = airlineList.getAirlineList().get(i); %>
    			<option <% if(airline.getCode().equals(preferredAirline.getCode())){ %> selected <% } %> value="<%=airline.getCode()%>"><%=airline.getName()%> (<%=airline.getCode()%>)</option>
    			<%}%> 
 
  			</select>
  		<br/>
  		<br/>
		  <input type="submit" value="Search!" />
		</form>
		
	 <br/>
      <a href="/AirportTUM/index.html">Go back</a>
        
    </body>
</html>

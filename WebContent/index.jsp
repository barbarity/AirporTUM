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
    <title>Search Flights</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="stylesheet2.css">
</head>
<body>
    
    <% if (departureCity.getId()!=-1 && arrivalCity.getId()!=-1 && preferredAirline.getCode() != null){ %>
   Here are the connections from <%= departureCity.getName() %> to  <%= arrivalCity.getName() %> using <%= preferredAirline.getName() %>.
   <%} %>
    <div class="container well">
     <form action="search" method="post">
	 	<h3>Search connections</h3>
	 	<br/>
	 	<div class="row">
	 	<div class="col-md-2">From</div>
	 	<div class="col-md-4">
		    <select name="departureCity" size="1">
		        <%for(int i=0;i< cityList.getCityList().size();i++){%>
    			<% CityBean city = cityList.getCityList().get(i); %>
    			<option <% if(city.getId() == departureCity.getId()){ %> selected <% } %> value="<%=city.getId()%>"><%=city.getName()%> (<%=city.getCountryName()%>)</option>
    			<%}%> 
  			</select>
  		</div> 
  		</div>
  		<br/>
  		<div class="row">
  		<div class="col-md-2">To</div>
  		<div class="col-md-4">
  			<select name="arrivalCity" size="1">
		        <%for(int i=0;i< cityList.getCityList().size();i++){%>
    			<% CityBean city = cityList.getCityList().get(i); %>
    			<option <% if(city.getId() == arrivalCity.getId()){ %> selected <% } %> value="<%=city.getId()%>"><%=city.getName()%> (<%=city.getCountryName()%>)</option>
    			<%}%> 
  			</select>
  		</div>
  		</div>
  		<br/>
  		<div class="row">
  		<div class="col-md-2">Airline</div>
  		<div class="col-md-4">
  			<select name="preferredAirline" size="1">
		        <%for(int i=0;i< airlineList.getAirlineList().size();i++){%>
    			<% AirlineBean airline = airlineList.getAirlineList().get(i); %>
    			<option <% if(airline.getCode().equals(preferredAirline.getCode())){ %> selected <% } %> value="<%=airline.getCode()%>"><%=airline.getName()%> (<%=airline.getCode()%>)</option>
    			<%}%> 
  			</select>
  		</div>
  		</div>
  		<br/>
  		<div class="row">
  		<div class="col-md-1">
		  <button class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Search</button>
		</div>
		</div>
		</form>
	</div>
		
	<div class="container well">
      <a href="/AirportTUM/index.html"><button class="btn btn-primary"><span class="glyphicon glyphicon-chevron-left"></span> Go back</button></a>
    </div>    
    </body>
</html>
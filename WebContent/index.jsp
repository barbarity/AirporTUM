<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="de.tum.in.dbpra.model.bean.CityBean" %>
<%@page import="de.tum.in.dbpra.model.bean.AirlineBean" %>
<%@page import="de.tum.in.dbpra.model.bean.CurrencyBean" %>

<jsp:useBean id="cityList" scope="request" class="de.tum.in.dbpra.model.bean.CityListBean" />
<jsp:useBean id="currencyList" scope="request" class="de.tum.in.dbpra.model.bean.CurrencyListBean" />
<jsp:useBean id="airlineList" scope="request" class="de.tum.in.dbpra.model.bean.AirlineListBean" />
<jsp:useBean id="preferredAirline" scope="request" class="de.tum.in.dbpra.model.bean.AirlineBean" />

<!DOCTYPE html>
<html>
<head>
    <title>AirporTUM</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/css/bootstrap-datetimepicker.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>
	<div class="container well">
		<div class="row">
		<div class="col-md-6">
     		<form action="search" method="post" class="form-inline">
	 		<h3>Search connections</h3>
	 		<br/>
	 		</div>
	 		</div>
	 		<div class="form-group row">
	 		<div class="col-md-2">
	 			<label for="departureCity">From:</label> 
	 		</div>
	 		<div class="col-md-6">
		    	<select id="departureCity" name="departureCity" class="form-control">
		        	<%for(int i=0;i< cityList.getCityList().size();i++){%>
    				<% CityBean city = cityList.getCityList().get(i); %>
    				<option value="<%=city.getId()%>"><%=city.getName()%> (<%=city.getCountryName()%>)</option>
    				<%}%> 
  				</select>
  			</div>
  			</div>
  			<div class="form-group row">
  			<div class="col-md-2">
	 			<label for="ariivalCity">To:</label>  
	 			</div>
	 			<div class="col-md-6">
  				<select id="arrivalCity" name="arrivalCity" class="form-control">
		        	<%for(int i=0;i< cityList.getCityList().size();i++){%>
    				<% CityBean city = cityList.getCityList().get(i); %>
    				<option value="<%=city.getId()%>"><%=city.getName()%> (<%=city.getCountryName()%>)</option>
    				<%}%> 
  				</select>
  				</div>
  			</div>
	 		<div class="form-group row">
	 		<div class="col-md-2">
	 			<label for="preferredAirline">Airline:</label> 
	 			</div>
	 			<div class="col-md-6">
  				<select name="preferredAirline" class="form-control">
    				<option value="">(No airline)</option>
		        	<%for(int i=0;i< airlineList.getAirlineList().size();i++){%>
    				<% AirlineBean airline = airlineList.getAirlineList().get(i); %>
    				<option value="<%=airline.getCode()%>"><%=airline.getName()%> (<%=airline.getCode()%>)</option>
    				<%}%> 
  				</select>
  				</div>
  			</div>
  			<div class="form-group row">
  			<div class="col-md-2">
	 			<label for="dateFrom">Departure Date:</label> 
	 		</div>
  			<div class="col-md-6">
            	<div class="input-group date" id="datetimepickerFrom">
            	    <input type="text" id="dateFrom" name="dateFrom" class="form-control" placeholder="From..." />
            	    <span class="input-group-addon">
                	    <span class="glyphicon glyphicon-calendar"></span>
            	    </span>
            </div>
            </div>
            </div>
            <div class="form-group row">
            <div class="col-md-2">
	 			<label for="dateTo">Until:</label> 
	 		</div>
            	<div class="col-md-6">
            	<div class="input-group date" id="datetimepickerTo">
                	<input type="text" id="dateTo" name="dateTo" class="form-control" placeholder="To..." />
                	<span class="input-group-addon">
                    	<span class="glyphicon glyphicon-calendar"></span>
                	</span>
            	</div>
            	</div>
        	</div>
        	<div class="form-group row">
        	<div class="col-md-2">
	 			<label for="className">Class:</label> 
	 		</div>
	 		<div class="col-md-6">
  				<select name="className" class="form-control">
    				<option value="economy">Economy</option>
    				<option value="first">First</option>
    				<option value="Business">Business</option>
  				</select>
  			</div>
  			</div>
  			<div class="form-group row">
  			<div class="col-md-2">
	 			<label for="people">Num. People:</label> 
	 		</div>
	 		<div class="col-md-6">
  				<select name="people" class="form-control">
    				<option value="1">1</option>
    				<option value="2">2</option>
    				<option value="3">3</option>
    				<option value="4">4</option>
    				<option value="5">5</option>
  				</select>
  			</div>
  			</div>
  		
  			<div class="form-group row">
  			<div class="col-md-2">
	 			<label for="currency">Currency:</label> 
	 		</div>
	 		<div class="col-md-6">
		    	<select id="currency" name="currency" class="form-control">
		        	<%for(int i=0;i< currencyList.getCurrencyList().size();i++){%>
    				<% CurrencyBean currency = currencyList.getCurrencyList().get(i); %>
    				<option value="<%=currency.getCurrencyCode()%>"><%=currency.getName()%> (<%=currency.getSymbol()%>)</option>
    				<%}%> 
  				</select>
  			</div>
  			</div>
		  <button type="submit" value="Search" class="btn btn-primary">Search <span class="glyphicon glyphicon-plane"></span></button>
		</form>
		</div>
	<script type="text/javascript">
    $(function () {
        $("#datetimepickerFrom").datetimepicker({
        	format: 'DD/MM/YYYY',
        	minDate: new Date(),
        	useCurrent: true
        });
        $("#datetimepickerTo").datetimepicker({
        	format: 'DD/MM/YYYY',
        	minDate: new Date(),
        	useCurrent: true
        });
        $("#datetimepickerFrom").on("dp.change", function (e) {
            $("#datetimepickerTo").data("DateTimePicker").minDate(e.date);
        });
    });
</script>
</body>
</html>
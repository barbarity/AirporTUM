<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.tum.in.dbpra.model.bean.BookingBean"%>
<%@page import="de.tum.in.dbpra.model.bean.PersonBean"%>
<%@page import="de.tum.in.dbpra.model.bean.CurrencyBean"%>
<%@page import="de.tum.in.dbpra.model.bean.FlightSegmentTicketBean"%>
<%@page import="de.tum.in.dbpra.model.bean.LuggageBean"%>


<jsp:useBean id="booking" scope="request" class="de.tum.in.dbpra.model.bean.BookingBean" />
<jsp:useBean id="checkinworker" scope="request" class="de.tum.in.dbpra.model.bean.PersonBean" />



<!DOCTYPE html>
<html>
<head>
    <title>AirporTUM</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
logged in as: ID #<%= checkinworker.getId() %> | <%= checkinworker.getEmail() %>
	<div class="container">
		<div class="row">
				<div class="col-sm-4">
					Booking #
				</div>
				<div class="col-sm-4">
					<%= booking.getId() %>
				</div>
		</div>
		<div class="row">
				<div class="col-sm-4">
					Name
				</div>
				<div class="col-sm-4">
					<%= booking.getPerson().getTitle()%> <%= booking.getPerson().getFirstName()%> <%= booking.getPerson().getLastName() %>
				</div>
		</div>
<div class="row">
				<div class="col-sm-4">
					Passport-ID
				</div>
				<div class="col-sm-4">
				     <%= booking.getPerson().getPassportId() %>
				</div>
		</div>
		<div class="row">
				<div class="col-sm-4">
					booked At
				</div>
				<div class="col-sm-4">
				     <%= booking.getBookedAtAirline().getName() %>
				</div>
		</div>
		<div class="row">
				<div class="col-sm-4">
					booked On
				</div>
				<div class="col-sm-4">
				     <%= booking.getBookingTimestamp() %>
				</div>
		</div>
		<div class="row">
				<div class="col-sm-4">
					Price
				</div>
				<div class="col-sm-4">
					<%= booking.getPrice() %> <%= booking.getCurrency().getSymbol() %>
				</div>
		</div>
		
	</div>
	
	
	<div class="row">
				<div class="col-sm-1">
					Flight Number
				</div>
				<div class="col-sm-1">
					Departure Date
				</div>
				<div class="col-sm-1">
					Departure Time
				</div>
				<div class="col-sm-1">
				    From Airport
				</div>
				<div class="col-sm-1">
					Arrival Time
				</div>
				<div class="col-sm-1">
				    To Airport
				</div>
		</div>

<% for (int i=0; i<booking.getFlightSegmentTicketList().size(); i++){ %>
<% FlightSegmentTicketBean fst = booking.getFlightSegmentTicketList().get(i); %>
	<div class="row">
				<div class="col-sm-1">
					<%= fst.getFlight().getOperatingAirline() %><%= fst.getFlight().getOperatingFlightNumber() %>
				</div>
				<div class="col-sm-1">
					<%= fst.getFlight().getDate() %>
				</div>
				<div class="col-sm-1">
					<%= fst.getFlight().getLocalDepartureTime() %>
				</div>
				<div class="col-sm-1">
				    <%= fst.getFlight().getDepartureAirport().getIATA() %>
				</div>
				<div class="col-sm-1">
					<%= fst.getFlight().getLocalArrivalTime() %>
				</div>
				<div class="col-sm-1">
				    <%= fst.getFlight().getArrivalAirport().getIATA() %>
				</div>
		</div>

<% } %>


<div class="row">
				<div class="col-sm-1">
					Luggage #
				</div>
				<div class="col-sm-1">
					Weight
				</div>
				<div class="col-sm-1">
					Height
				</div>
				<div class="col-sm-1">
				    Width
				</div>
				<div class="col-sm-1">
					Length
				</div>
				<div class="col-sm-1">
				    Additional Price
				</div>
		</div>

<% for (int i=0; i<booking.getLuggageList().size(); i++){ %>
<% LuggageBean luggageItem = booking.getLuggageList().get(i); %>
	<div class="row">
				<div class="col-sm-1">
					<%= luggageItem.getId() %>
				</div>
				<div class="col-sm-1">
					<%= luggageItem.getWeight() %>
				</div>
				<div class="col-sm-1">
					<%= luggageItem.getHeight() %>
				</div>
				<div class="col-sm-1">
					<%= luggageItem.getWidth() %>
				</div>
				<div class="col-sm-1">
					<%= luggageItem.getLength() %>
				</div>
				<div class="col-sm-1">
					<%= luggageItem.getAdditionalPrice() %> <%= booking.getCurrency().getSymbol() %>
				</div>
				
		</div>

<% } %>


				
				
				<div class="col-sm-2">
					<a href="/AirportTUM/login">Check in another booking</a>
				</div>


</body>
</html>

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
<html lang="en">
<head>
    <title>Booking Overview</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="stylesheet2.css">
</head>
<body style="background-image:url(A343.jpg)">
<div class="container well">
<div class="row">
	<form action="login" method="post">
	<div class="col-md-offset-6 col-md-6">
		<input type="hidden" name="logoutUser" value="<%= checkinworker.getEmail() %>" />			
	<h4 class="pull-right">logged in as: ID #<%= checkinworker.getId() %> | <%= checkinworker.getEmail() %> </h4>
	</div>
	<div class="row">
	<div class="col-md-offset-6 col-md-6">					
		<button type="submit">Logout</button>
	</div>
	</div>
</form>
</div>
</div>

<% if(booking.getBookingTimestamp()==null){ %>
					No valid booking id! please <a href="/AirportTUM/login">try again</a>
				<% }else if(booking.getCheckedInOn()!=null){ %>
					This has already been checked in! <a href="/AirportTUM/login">Check in another booking</a>
				<% }else{ %>
<div class="container well">
		<table class="table table-striped">
		<tr>
				<td class="col-md-4">
					Booking ID
				</td>
				<td class="col-md-4">
					<%= booking.getId() %>
				</td>
		</tr>
		<tr>
				<td class="col-md-4">
					Name
				</td>
				<td class="col-md-4">
					<%= booking.getPerson().getTitle()%> <%= booking.getPerson().getFirstName()%> <%= booking.getPerson().getLastName() %>
				</td>
		</tr>
<tr>
				<td class="col-md-4">
					Passport-ID
				</td>
				<td class="col-md-4">
				     <%= booking.getPerson().getPassportId() %>
				</td>
		</tr>
		<tr>
				<td class="col-md-4">
					Booked at
				</td>
				<td class="col-md-4">
				     <%= booking.getBookedAtAirline().getName() %>
				</td>
		</tr>
		<tr>
				<td class="col-md-4">
					Booked on
				</td>
				<td class="col-md-4">
				     <%= booking.getBookingTimestamp() %>
				</td>
		</tr>
		<tr>
				<td class="col-md-4">
					Price

				</td>
				<td class="col-md-4">
					<%= booking.getPrice() %> <%= booking.getCurrency().getSymbol() %>
				</td>
		</tr>
		</table>
</div>
		


	
<div class="container well">
<table class="table table-striped">
<thead>
	<tr>
				<th class="col-md-2">
					Flight Number
				</th>
				<th class="col-md-2">
					Departure Date
				</th>
				<th class="col-md-2">
					Departure Time
				</th>
				<th class="col-md-2">
				    From Airport
				</th>
				<th class="col-md-2">
					Arrival Time
				</th>
				<th class="col-md-2">
				    To Airport
				</th>
		</tr>
		</thead>
		<tbody>

<% for (int i=0; i<booking.getFlightSegmentTicketList().size(); i++){ %>
<% FlightSegmentTicketBean fst = booking.getFlightSegmentTicketList().get(i); %>

	<tr>
				<td class="col-sm-1">
					<div data-toggle="tooltip" data-placement="top" title="Operated by <%= fst.getFlight().getOperatingAirline().getName() %>"><%= fst.getBookedFlightNumber() %></div>
				</td>
				<td class="col-sm-1">
					<%= fst.getFlight().getDate() %>
				</td>
				<td class="col-md-2">
					<%= fst.getFlight().getLocalDepartureTime() %>
				</td>
				<td class="col-md-2">
				    <%= fst.getFlight().getDepartureAirport().getIATA() %>
				</td>
				<td class="col-md-2">
					<%= fst.getFlight().getLocalArrivalTime() %>
				</td>
				<td class="col-md-2">
				    <%= fst.getFlight().getArrivalAirport().getIATA() %>
				</td>
		</tr>
		<% } %>
		</tbody>
</table>


<table class="table table-striped">
<thead>
<tr>
				<th class="col-md-2">
					Luggage #
				</th>
				<th class="col-md-2">
					Weight
				</th>
				<th class="col-md-2">
					Height
				</th>
				<th class="col-md-2">
				    Width
				</th>
				<th class="col-md-2">
					Length
				</th>
				<th class="col-md-2">
				    Additional Price
				</th>
</tr>
</thead>
<tbody>
<% for (int i=0; i<booking.getLuggageList().size(); i++){ %>
<% LuggageBean luggageItem = booking.getLuggageList().get(i); %>
	<tr>
				<td class="col-md-2">
					<%= luggageItem.getId() %>
				</td>
				<td class="col-md-2">
					<%= luggageItem.getWeight() %>
				</td>
				<td class="col-md-2">
					<%= luggageItem.getHeight() %>
				</td>
				<td class="col-md-2">
					<%= luggageItem.getWidth() %>
				</td>
				<td class="col-md-2">
					<%= luggageItem.getLength() %>
				</td>
				<td class="col-md-2">
					<%= luggageItem.getAdditionalPrice() %> <%= booking.getCurrency().getSymbol() %>
				</td>
	</tr>
		<tr>
		<td class="col-md-2">
				<form action="bookingOverview" method="post">
							<input type="hidden" name="bookingid" value="<%= booking.getId() %>" />							
							<input type="hidden" name="deleteLuggage" value="<%= luggageItem.getId() %>" />
							<button type="submit" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Delete</button>
				</form>
				</td>
				<td class="col-md-2">
				<form action="bookingOverview" method="post">
							<input type="hidden" name="edit" value="<%= luggageItem.getId() %>" />
						<button type="submit" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span> Edit</button>
				</form>
				</td>
				

		</tr>
		<% } %>
		</tbody>
</table>

</div>
<div class="container well">
	<div class="row">
	<div class="col-md-2">
	<form action="bookingOverview" method="post">
			<input type="hidden" name="bookingid" value="<%= booking.getId() %>" />							
			<input type="hidden" name="rollback" value="<%= 1 %>" />
			<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Undo changes</button>
	</form>
	</div>
	<div class="col-md-2">
	<form action="bookingOverview" method="post">
			<input type="hidden" name="bookingid" value="<%= booking.getId() %>" />							
			<input type="hidden" name="addNewLuggage" value="<%= 1 %>" />
			<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Add Luggage</button>
	</form>
	</div>
	<div class="col-md-2">
		<form action="bookingOverview" method="post">	
			<input type="hidden" name="bookingid" value="<%= booking.getId() %>" />							
			<input type="hidden" name="commit" value="<%= 1 %>" />
			<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> Finish check-in</button>
		</form>
	</div>
	</div>
</div>

				<% } %>

</body>
</html>

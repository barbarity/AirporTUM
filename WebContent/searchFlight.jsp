<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="de.tum.in.dbpra.model.bean.CityBean"%>
<%@page import="de.tum.in.dbpra.model.bean.AirlineBean"%>
<%@page import="de.tum.in.dbpra.model.bean.FlightBean"%>
<%@page import="de.tum.in.dbpra.model.bean.ConnectionBean"%>

<jsp:useBean id="connectionList" scope="request"
	class="de.tum.in.dbpra.model.bean.ConnectionListBean" />
<jsp:useBean id="cityList" scope="request"
	class="de.tum.in.dbpra.model.bean.CityListBean" />
<jsp:useBean id="departureCity" scope="request"
	class="de.tum.in.dbpra.model.bean.CityBean" />
<jsp:useBean id="arrivalCity" scope="request"
	class="de.tum.in.dbpra.model.bean.CityBean" />
<jsp:useBean id="airlineList" scope="request"
	class="de.tum.in.dbpra.model.bean.AirlineListBean" />
<jsp:useBean id="preferredAirline" scope="request"
	class="de.tum.in.dbpra.model.bean.AirlineBean" />


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
<%  DecimalFormat myFormatter = new DecimalFormat("###,###.00");
	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm"); %>
	<div class="container">
		<div class="row">
			<div class="col-sm-10">
			<% for (int i = 0; i < connectionList.getConnectionList().size(); i++) {
				ConnectionBean currentConnection = connectionList.getConnectionList().get(i);
				int size = currentConnection.getFlightList().size(); %>
				<table class="table table-hover">
					<thead>
						<tr>
							<th colspan="3">
								<h2><%= currentConnection.getFlightList().get(0).getDepartureAirport().getIATA() %> <span class="glyphicon glyphicon-arrow-right"></span> <%= currentConnection.getFlightList().get(size-1).getArrivalAirport().getIATA() %></h2>
							</th>
							<th class="text-right"><h4><%= currentConnection.getFlightList().size() %> stops</h4></th>
							<th class="text-right"><h4><%= currentConnection.getOverallDuration() %></h4></th>
							<th class="text-right"><h4><%= myFormatter.format(currentConnection.getOverallPrice()) %> <%= currentConnection.getCurrency().getSymbol() %></h4></th>
						</tr>
					</thead>
					<tbody>
						<% for (FlightBean currentFlight: currentConnection.getFlightList()) { %>
						<tr>
							<td><%= currentFlight.getOperatingAirline().getName() %>ss</td>
							<td><%= dateFormat.format(currentFlight.getLocalDepartureTime()) %> <%= currentFlight.getDepartureCity().getName() %></td>
							<td class="text-center"><span class="glyphicon glyphicon-arrow-right"></span></td>
							<td class="text-right"><%= dateFormat.format(currentFlight.getLocalArrivalTime()) %> <%= currentFlight.getArrivalCity().getName() %></td>
							<td class="text-right"><%= dateFormat.format(currentFlight.getDuration()) %> h</td>
							<td class="text-right"><%= myFormatter.format(currentFlight.getPrice()) %> <%= currentConnection.getCurrency().getSymbol() %></td>
						</tr>
						<% } %>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6" class="text-right">
								<form action="pay" method="post">
									<% for (int j = 0; j < connectionList.getConnectionList().get(i).getFlightList().size(); j++) { %>
									<input type="hidden" name="flights" value="<%= connectionList.getConnectionList().get(i).getFlightList().get(j).getFlightId() %>" />
									<% } %>
									<input type="hidden" name="peopleLeft" value="<%= request.getParameter("people") %>" />
									<input type="hidden" name="className" value="<%= request.getParameter("className") %>" />
									<input type="hidden" name="currency" value="" />
									<button type="submit" class="btn btn-primary">Book</button>
								</form>
							</td>
						</tr>
					</tfoot>
				</table>
			<% } %>
			</div>
			<div class="col-sm-2">
				Here we could use some filtering...
			</div>
		</div>
	</div>

</body>
</html>

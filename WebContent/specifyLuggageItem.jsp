<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.tum.in.dbpra.model.bean.LuggageBean"%>
<%@page import="de.tum.in.dbpra.model.bean.PersonBean"%>
<%@page import="de.tum.in.dbpra.model.bean.BookingBean"%>


<jsp:useBean id="luggage" scope="request" class="de.tum.in.dbpra.model.bean.LuggageBean" />
<jsp:useBean id="booking" scope="request" class="de.tum.in.dbpra.model.bean.BookingBean" />

<jsp:useBean id="checkinworker" scope="request" class="de.tum.in.dbpra.model.bean.PersonBean" />


<!DOCTYPE html>
<html>
<head>
    <title>AirporTUM</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
logged in as: ID #<%= checkinworker.getId() %> | <%= checkinworker.getEmail() %> <form action="login" method="post">
							<input type="hidden" name="logoutUser" value="<%= checkinworker.getEmail() %>" />							
							<button type="submit">Logout</button>
				</form>

	<div class="container">
		<div class="row">
			<div class="col-sm-7">
				
					<h2>Luggage #<%= luggage.getId() %> </h2>
				<form action="bookingOverview" method="post">
						<div class="form-group">
							<label for="weight">Weight</label>
							<input type="text" class="form-control" name="weight" id="weight" value="<%= luggage.getWeight() %>" placeholder="weight">
						</div> g
						<div class="form-group">
							<label for="height">Height</label>
							<input type="text" class="form-control" name="height" id="height" value="<%= luggage.getHeight() %>" placeholder="height">
						</div> cm
						<div class="form-group">
							<label for="width">Width</label>
							<input type="text" class="form-control" name="width" id="width" value="<%= luggage.getWidth() %>" placeholder="width">
						</div> cm
						<div class="form-group">
							<label for="length">Length</label>
							<input type="text" class="form-control" name="length" id="length" value="<%= luggage.getLength() %>" placeholder="length">
						</div> cm
						<%= luggage.getAdditionalPrice() %> <%= booking.getCurrency().getSymbol() %>
							<input type="hidden" name="luggageId" value="<%= luggage.getId() %>" />
							<button type="submit" name="calculateCostButton" value="1">Calculate Additional Cost</button>
							<button type="submit" name="addLuggage" value="1">OK</button>
							
				</form>					
				
				</div>
			</div>

		</div>
		

</body>
</html>

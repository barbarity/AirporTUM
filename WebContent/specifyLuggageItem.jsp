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
<div class="container well">
<div class="row">
	<form action="login" method="post">
	<div class="col-md-offset-6 col-md-6">
		<input type="hidden" name="logoutUser" value="<%= checkinworker.getEmail() %>" />			
	<h4 class="pull-right">logged in as: ID #<%= checkinworker.getId() %> | <%= checkinworker.getEmail() %> </h4>
	</div>
	<div class="row">
	<div class="col-md-offset-10 col-md-2">					
		<button type="submit" class="btn btn-primary pull-right">Logout</button>
	</div>
	</div>
</form>
</div>
</div>

	<div class="container well">
		<div class="row">
			<div class="col-md-6">		
					<h2>Luggage Number <%= luggage.getId() %> </h2>
					<br/>
			</div>
			</div>
				<form action="bookingOverview" method="post">
						<div class="form-group row">
						<div class="col-md-2">
							<label for="weight">Weight in g</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" name="weight" id="weight" value="<%= luggage.getWeight() %>" placeholder="weight">
						</div>
						</div>
						<div class="form-group row">
						<div class="col-md-2">
							<label for="height">Height in cm</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" name="height" id="height" value="<%= luggage.getHeight() %>" placeholder="height">
						</div>
						</div>
						<div class="form-group row">
						<div class="col-md-2">
							<label for="width">Width in cm</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" name="width" id="width" value="<%= luggage.getWidth() %>" placeholder="width">
						</div>
						</div>
						<div class="form-group row">
						<div class="col-md-2">
							<label for="length">Length in cm</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" name="length" id="length" value="<%= luggage.getLength() %>" placeholder="length">
						</div>
						</div>
						<div class="row">
						<div class="col-md-2">
							<h4>Additional cost</h4>
						</div>
						<div class="col-md-6">
							<h4><%= luggage.getAdditionalPrice() %> <%= booking.getCurrency().getSymbol() %></h4>
							<br/>
						</div>
						<div class="row">
						<div class="col-md-6">
							<input type="hidden" name="luggageId" value="<%= luggage.getId() %>" />
						</div>
						</div>
						</div>	
						<button type="submit" name="calculateCostButton" value="1" class="btn btn-primary">Calculate Additional Cost</button>
							<button type="submit" name="addLuggage" value="1" class="btn btn-primary">OK</button>
							
				</form>					
				
				</div>
			</div>

		</div>
		

</body>
</html>

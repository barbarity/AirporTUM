<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.tum.in.dbpra.model.bean.PersonBean"%>


<jsp:useBean id="checkinworker" scope="request" class="de.tum.in.dbpra.model.bean.PersonBean" />


<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
<div class="container well">
<div class="row">
	<form action="login" method="post">
	<div class="col-md-offset-6 col-md-6">
		<input type="hidden" name="logoutUser" value="<%= checkinworker.getEmail() %>" />			
	<h4 class="pull-right">logged in as: ID #<%= checkinworker.getId() %> | <%= checkinworker.getEmail() %> </h4>
	</div>
	<div class="row">
	<div class="col-md-offset-6 col-md-6">					
		<button type="submit" class="btn btn-primary pull-right">Logout</button>
	</div>
	</div>
</form>
</div>
</div>
	<div class="container well">
		<div class="row">
			<div class="col-md-6">
					<h3>Enter Booking-Id:</h3>
			</div>
		</div>
					<form class="form-horizontal" action="bookingOverview" method="post">
						<div class="form-group row">
						<div class="col-md-2">
							<label for="bookingid" class="control-label">Booking-Id</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" name="bookingid" id="bookingid" placeholder="Booking-ID...">
						</div>
						</div>
						<div class="row">
						<div class="col-md-2">
							<button type="submit" class="btn btn-success">Submit <span class="glyphicon glyphicon-plane"></span></button>
						</div>
						</div>
					</form>
	</div>			
</body>
</html>

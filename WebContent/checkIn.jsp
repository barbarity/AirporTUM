<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.tum.in.dbpra.model.bean.PersonBean"%>


<jsp:useBean id="checkinworker" scope="request" class="de.tum.in.dbpra.model.bean.PersonBean" />


<!DOCTYPE html>
<html>
<head>
    <title>AirporTUM</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
   <!--  
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.js"></script> --> 
</head>
<body>
logged in as: ID #<%= checkinworker.getId() %> | <%= checkinworker.getEmail() %>  <form action="login" method="post">
							<input type="hidden" name="logoutUser" value="<%= checkinworker.getEmail() %>" />							
							<button type="submit">Logout</button>
				</form>
	<div class="container">
		<div class="row">
			<div class="col-sm-7">
				<div class="row">
					<h2>Enter Booking-Id:</h2>
					<form class="form-horizontal" action="bookingOverview" method="post">
						<div class="form-group">
							<label for="bookingid" class="col-sm-3 control-label">Booking-Id</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="bookingid" id="bookingid" placeholder="Booking-ID...">
							</div>
						</div>
						
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</form>
				</div>
				
			</div>
		</div>
		
	</div>

</body>
</html>

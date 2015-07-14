<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.tum.in.dbpra.model.bean.CityBean"%>
<%@page import="de.tum.in.dbpra.model.bean.AirlineBean"%>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/css/bootstrap-datetimepicker.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.14.30/js/bootstrap-datetimepicker.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-7">
				<div class="row">
					<h2>Login:</h2>
					<form class="form-horizontal" action="login" method="post">
						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">Username</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="username" id="username" placeholder="Username...">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">Password</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" name="password" id="password" placeholder="Password...">
							</div>
						</div>
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit"  name="login" class="btn btn-default">Login</button>
						</div>
					</form>
				</div>
				<div class="row">
					<h2>...or Register:</h2>
					<form class="form-horizontal" action="register">
						<div class="form-group">
							<label for="username">Username</label>
							<input type="text" class="form-control" name="username" id="username" placeholder="Username...">
						</div>
						<div class="form-group">
							<label for="email">Email</label>
							<input type="email" class="form-control" name="email" id="email" placeholder="Email...">
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" class="form-control" name="password" id="password" placeholder="Password...">
						</div>
						<div class="form-group">
							<label for="repeatpassword">Password</label>
							<input type="password" class="form-control" name="repeatpassword" id="repeatpassword" placeholder="Repeat Password...">
						</div>
						<div class="form-group">
							<select class="form-control" name="title" id="title">
								<option value="mr">Mr.</option>
								<option value="ms">Ms.</option>
								<option value="dr">Dr.</option>
							</select>
						</div>
						<div class="form-group">
							<label for="firstname">First Name</label>
							<input type="text" class="form-control" name="firstname" id="firstname" placeholder="First name...">
						</div>
						<div class="form-group">
							<label for="lastname">Last Name</label>
							<input type="text" class="form-control" name="lastname" id="lastname" placeholder="Last name...">
						</div>
						<div class="form-group">
							<label for="gender">Gender</label>
							<select class="form-control" name="gender" id="gender">
								<option value="male">Male</option>
								<option value="female">Female</option>
								<option value="other">Other?</option>
							</select>
						</div>
						<div class="form-group">
							<label for="birthdate">Birth Date</label>
							<div class="input-group date" id="datetimepicker">
                				<input type="text" class="form-control" name="birthdate" class="birthdate" />
                				<span class="input-group-addon">
                    				<span class="glyphicon glyphicon-calendar">
                    				</span>
                				</span>
            				</div>
						</div>
						<div class="form-group">
							<label for="address">Address</label>
							<input type="text" class="form-control" name="address" id="address" placeholder="Address...">
						</div>
						<div class="form-group">
							<label for="phone">Phone</label>
							<input type="tel" class="form-control" name="phone" id="phone" placeholder="Phone...">
						</div>
						<button type="submit" name="register" class="btn btn-default">Register</button>
					</form>
				</div>
			</div>
			<div class="col-sm-5">
				Ticket about the flight goes here...
			</div>
		</div>
		<script type="text/javascript">
        $(function () {
            $('#datetimepicker').datetimepicker({
                viewMode: 'years',
                format: 'DD/MM/YYYY'
            });
        });
    </script>
		
	</div>

</body>
</html>

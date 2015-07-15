<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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
			<div class="col-md-7">
					<h2>Check-In-Worker Login:</h2>
					<br/>
			</div>
			</div>
					<form class="form-horizontal" action="login" method="post">
						<div class="form-group row">
						<div class="col-md-2">
							<label for="username" class="col-sm-3 control-label">Username</label>
						</div>
						<div class="col-md-6">
								<input type="text" class="form-control" name="username" id="username" placeholder="Username...">
						</div>
						</div>
						<div class="form-group row">
						<div class="col-md-2">
							<label for="password" class="col-sm-3 control-label">Password</label>
						</div>
						<div class="col-md-6">
							<input type="password" class="form-control" name="password" id="password" placeholder="Password...">
						</div>
						</div>
						<div class="row">
						<div class="col-sm-9">
							<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-plane"></span>     Login</button>
						</div>
						</div>
					</form>
				</div>
				
			</div>
		</div>
		
	</div>

</body>
</html>

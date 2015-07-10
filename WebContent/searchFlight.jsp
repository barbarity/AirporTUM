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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-10">
			<% for (int i = 0; i < connectionList.getConnectionList().size(); i++) { %>
				<div class="row">
					<div class="col-sm-10">
						Here goes info about the connection. Like the price: <%= connectionList.getConnectionList().get(i).getOverallPrice() %>
					</div>
					<div class="col-sm-2">
						<form action="pay" method="post">
							<% for (int j = 0; j < connectionList.getConnectionList().get(i).getFlightList().size(); j++) { %>
							<input type="hidden" name="flights[]" value="<%= connectionList.getConnectionList().get(i).getFlightList().get(j).getFlightId() %>" />
							<% } %>
							<button type="submit">Book</button>
						</form>
					</div>
				</div>
			<% } %>
			</div>
			<div class="col-sm-2">
				Here we could use some filtering...
			</div>
		</div>
	</div>

</body>
</html>

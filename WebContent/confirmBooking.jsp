<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.tum.in.dbpra.model.bean.CityBean"%>
<%@page import="de.tum.in.dbpra.model.bean.AirlineBean"%>
<%@page import="de.tum.in.dbpra.model.bean.FlightBean"%>
<%@page import="de.tum.in.dbpra.model.bean.PersonBean"%>
<%@page import="java.util.ArrayList"%>

<jsp:useBean id="connectionBean" scope="request"
	class="de.tum.in.dbpra.model.bean.ConnectionBean" />
<%
	ArrayList<PersonBean> people = (ArrayList<PersonBean>) request.getAttribute("people");
%>

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
			<div class="col-sm-offset-1 col-sm-10">
				<div class="row text-center">
					<h2>Confirm Booking?</h2>
					<form class="form-horizontal" action="confirm" method="post">
						<button type="submit"  name="confirm" class="btn btn-primary">Confirm!</button>
						<% for (int j = 0; j < connectionBean.getFlightList().size(); j++) { %>
						<input type="hidden" name="flights" value="<%= connectionBean.getFlightList().get(j).getFlightId() %>" />
						<% } %>
						<input type="hidden" name="peopleLeft" value="<%= request.getAttribute("peopleLeft") %>" />
						<% for (int j = 0; j < people.size(); j++) { %>
						<input type="hidden" name="people" value="<%= people.get(j).getId() %>" />
						<% } %>
						<input type="hidden" name="className" value="<%= request.getAttribute("className") %>" />
						<input type="hidden" name="currency" value="" />
					</form>
				</div>
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

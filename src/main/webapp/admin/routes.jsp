<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import ="angela.kuznetsova.assignment2.RouteDAO" import ="angela.kuznetsova.assignment2.Route"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Route Management</title>
</head>
<body>
<% if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) { %>
	<h1>Routes</h1>

	<table style="with: 50%">
		<tr>
			<th>ID</th>
			<th>Source</th>
			<th>Destination</th>
			<th>Number of Places</th>
			<th>Price, $</th>
			<th>Time of Departure</th>
		</tr>
	<%
	RouteDAO routeDAO = new RouteDAO();
	for (Route route : routeDAO.getAll()) { 
	%>
	<tr>
	<td><%= route.getId() %></td>
	<td><%= route.getSource() %></td>
	<td><%= route.getDestination() %></td>
	<td><%= route.getNumberOfSeats() %></td>
	<td><%= route.getPricePerSeat() %></td>
	<td><%= route.getDate() %></td>
	</tr>
   <% } %>
	</table>
		<form action="AddRoute" method="post">
			<h1>Add Route</h1>

			<table style="with: 50%">
				<tr>
					<th>Source</th>
					<td><input type="text" name="source" /></td>
				</tr>
				<tr>
					<th>Destination</th>
					<td><input type="text" name="destination" /></td>
				</tr>
				<tr>
					<th>Number of Places</th>
					<td><input type="number" name="number_places" /></td>
				</tr>
				<tr>
					<th>Price, $</th>
					<td><input type="number" name="price" /></td>
				</tr>
				<tr>
					<th>Time of Departure (dd/MM/yy HH:mm)</th>
					<td><input type="datetime-local" name="depart_time" /></td>
				</tr>

			</table>
			<input type="submit" value="Submit" />
	

		</form>
<% } else { %>    
Please <a href="../Login">Login</a>
<% } %>
</body>
</html>
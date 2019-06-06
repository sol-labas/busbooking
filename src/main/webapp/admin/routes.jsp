<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="angela.kuznetsova.assignment2.RouteDAO"
	import="angela.kuznetsova.assignment2.Route"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Route Management</title>
</head>
<body>
	<!-- checking is user has admin role -->
	<%
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) {
	%>
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
		<!-- get information about all routes -->
		<%
			RouteDAO routeDAO = new RouteDAO();
				for (Route route : routeDAO.getAll()) {
		%>
		<tr>
			<td><%=route.getId()%></td>
			<td><%=route.getSource()%></td>
			<td><%=route.getDestination()%></td>
			<td><%=route.getNumberOfSeats()%></td>
			<td><%=route.getPricePerSeat()%></td>
			<td><%=route.getDate()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<!-- add route -->
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
				<td><input type="datetime" name="depart_time" /></td>
			</tr>

		</table>
		<input type="submit" value="Submit" />


	</form>

	<table>
		<tr>
			<td colspan="2" style="color: red;">
				<!-- if success we see message--> <%
 	if ((String) request.getAttribute("successAdd") != null) {
 %> <%=(String) request.getAttribute("successAdd")%> <%
 	}
 %>
			</td>
		</tr>
	</table>
	<!-- delete route -->
	<form action="DeleteRoute" method="post">
		<h1>Delete Route</h1>

		<table style="with: 50%">
			<tr>
				<th>Route</th>
				<td><select name="routeid">
						<%
							for (Route route : routeDAO.getAll()) {
						%>
						<option value="<%=route.getId()%>">
							<%=route.getSource()%> -
							<%=route.getDestination()%> on
							<%=route.getDate()%>, $<%=route.getPricePerSeat()%>
						</option>
						<%
							}
						%>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="Submit" />


	</form>
	<table>
		<tr>
			<td colspan="2" style="color: red;">
				<!-- if success we see message--> <%
 	if ((String) request.getAttribute("successDelete") != null) {
 %> <%=(String) request.getAttribute("successDelete")%> <%
 	}
 %>
			</td>
		</tr>
	</table>
	<%
		} else {
	%>
	Please
	<a href="../Login">Login</a>
	<%
		}
	%>
</body>
</html>
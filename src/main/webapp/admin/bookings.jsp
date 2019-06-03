<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import ="angela.kuznetsova.assignment2.BookingDAO" 
	import ="angela.kuznetsova.assignment2.Booking"
	import="angela.kuznetsova.assignment2.Route"
	import="angela.kuznetsova.assignment2.RouteDAO"
	import="angela.kuznetsova.assignment2.User"
	import="angela.kuznetsova.assignment2.UserDAO"
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookings</title>
</head>
<body>
	<%
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) {
	%>
	<h1>All Bookings</h1>

	<table style="with: 50%">
		<tr>
			<th>Booking ID</th>
			<th>Source</th>
			<th>Destination</th>
			<th>Departure Day and Time</th>
			<th>Number of Places</th>
			<th>Price, $</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
	<%
		BookingDAO bookingDAO = new BookingDAO();
			RouteDAO routeDAO = new RouteDAO();
			UserDAO userDAO = new UserDAO();
			for (Booking booking : bookingDAO.getAll()) {
				Route route = routeDAO.getRoutebyId(booking.getRouteId());
				User user = userDAO.getUserById(booking.getUserId());
	%>
	<tr>
		<td><%=booking.getId()%></td>
		<td><%=route.getSource()%></td>
		<td><%=route.getDestination()%></td>
		<td><%=route.getDate()%></td>
		<td><%=user.getFirstName()%></td>
		<td><%=user.getLastName()%></td>
	</tr>
	<%
		}
	%>
	</table>
	<form action="DeleteBookingAdmin" method="post">
		<h1>Delete Booking</h1>

		<table style="with: 50%">
			<tr>
				<th>Booking</th>
				<td><select name="bookingid">
						<%
							for (Booking booking : bookingDAO.getAll()) {
								Route route = routeDAO.getRoutebyId(booking.getRouteId());
								User user = userDAO.getUserById(booking.getUserId());
						%>
						<option value="<%=booking.getId()%>">
							<%=route.getSource()%> -
							<%=route.getDestination()%> on
							<%=route.getDate()%>, $<%=route.getPricePerSeat()%>
							booked by <%=user.getFirstName()%> <%=user.getLastName()%> 
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
				<%
					if ((String) request.getAttribute("success") != null) {
				%> <%=(String) request.getAttribute("success")%>
				<%
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
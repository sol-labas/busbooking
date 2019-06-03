<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="angela.kuznetsova.assignment2.BookingDAO"
	import="angela.kuznetsova.assignment2.Route"
	import="angela.kuznetsova.assignment2.RouteDAO"
	import="angela.kuznetsova.assignment2.Booking"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
</head>
<body>
	<%
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("user")) {
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
		</tr>
		<%
			BookingDAO bookingDAO = new BookingDAO();
				RouteDAO routeDAO = new RouteDAO();
				for (Booking booking : bookingDAO.getByUserID((Long)session.getAttribute("id"))) {
					Route route = routeDAO.getRoutebyId(booking.getRouteId());
		%>
		<tr>
			<td><%=booking.getId()%></td>
			<td><%=route.getSource()%></td>
			<td><%=route.getDestination()%></td>
			<td><%=route.getDate()%></td>
			<td><%=booking.getNumberOfSeats()%></td>
			<td><%=booking.getPrice()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<form action="AddBooking" method="post">
		<h1>Add Booking</h1>

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
			<tr>
				<th>Number of places</th>
				<td><select name="number_places">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
				</select></td>
			</tr>

		</table>
		<input type="submit" value="Submit" />
	</form>
	<table>
		<tr>
			<td colspan="2" style="color: red;">
				<%
					if ((String) request.getAttribute("successAdd") != null) {
				%> <%=(String) request.getAttribute("successAdd")%> <%
 	}
 %>
			</td>
		</tr>
	</table>

	</table>
	<form action="DeleteBookingUser" method="post">
		<h1>Delete Booking</h1>

		<table style="with: 50%">
			<tr>
				<th>Booking</th>
				<td><select name="bookingid">
						<%
							for (Booking booking : bookingDAO.getByUserID((Long) session.getAttribute("id"))) {
								Route route = routeDAO.getRoutebyId(booking.getRouteId());
						%>
						<option value="<%=booking.getId()%>">
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
				<%
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Bus booking system</title>
</head>
<body>
<% if (session.getAttribute("role") != null && session.getAttribute("role").equals("user")) { %>
    <h2>Welcome to Bus bookings</h2>
    <ul>
    	<li><a href="Bookings">Your bookings</a></li>
    	<li><a href="AddBooking">Add booking</a></li>
    	<li><a href="../Logout">Logout</a></li>
    </ul>
<% } else { %>    
Please <a href="../Login">Login</a>
<% } %>
</body>
</html>


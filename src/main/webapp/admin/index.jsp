<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Bus booking system</title>
</head>
<body>
<% if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) { %>
    <h2>Welcome to administration</h2>
    <ul>
    	<li><a href="routes.jsp">Route management</a></li>
    	<li><a href="bookings.jsp">View and delete bookings</a></li>
    	<li><a href="users.jsp">View and delete users</a></li> 	
    	<li><a href="../Logout">Logout</a></li>
    </ul>
<% } else { %>    
Please <a href="../Login">Login</a>
<% } %>
</body>
</html>


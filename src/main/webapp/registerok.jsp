<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Bus booking system</title>
</head>
<body>
	<% String username = request.getParameter("username"); %>
    <h2>Welcome <% out.println(username); %> !!!! You have registered.</h2>
    
    Now you can <a href="login.jsp">Login</a>.
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<form action="Login" method="post">
  <table style="with: 50%">
	 <tr>
	 	<td colspan="2" style="color:red;">
	 		<% if ((String)request.getAttribute("error") != null) { %>
	 		<%= (String)request.getAttribute("error") %>
	 		<% } %>
	 	</td>
	 </tr>
			<tr>
				<td>UserName</td>
				<td><input type="text" name="username" /></td>
			</tr>
				<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
		</table>
		<input type="submit" value="Login" /></form>

</body>
</html>
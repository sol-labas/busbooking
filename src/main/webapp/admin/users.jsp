<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="angela.kuznetsova.assignment2.UserDAO"
	import="angela.kuznetsova.assignment2.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management</title>
</head>
<body>
	<%
		if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) {
	%>
	<h1>All Users</h1>

	<table style="with: 50%">
		<tr>
			<th>User ID</th>
			<th>Login</th>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<%
			UserDAO userDAO = new UserDAO();
				for (User user : userDAO.getAll()) {
		%>
		<tr>
			<td><%=user.getId()%></td>
			<td><%=user.getUsername()%></td>
			<td><%=user.getFirstName()%></td>
			<td><%=user.getLastName()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<form action="DeleteUser" method="post">
		<h1>Delete Booking</h1>

		<table style="with: 50%">
			<tr>
				<th>User</th>
				<td><select name="userid">
						<%
							for (User user : userDAO.getAll()) {
						%>
						<option value="<%=user.getId()%>">
							<%=user.getUsername()%>
							<%=user.getFirstName()%>
							<%=user.getLastName()%>
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
package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angela.kuznetsova.assignment2.User;
import angela.kuznetsova.assignment2.UserDAO;

public class Registration extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// TODO: Check if no users (first user created - then use ADMIN role
		
		User user = new User(0, firstName, lastName, username, password, User.USER);
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.insert(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Could not save user");
		}

		RequestDispatcher req = request.getRequestDispatcher("registerok.jsp");
		req.forward(request, response);
	}
}

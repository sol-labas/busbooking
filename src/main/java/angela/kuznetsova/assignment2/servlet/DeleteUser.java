package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angela.kuznetsova.assignment2.BookingDAO;
import angela.kuznetsova.assignment2.User;
import angela.kuznetsova.assignment2.UserDAO;

public class DeleteUser extends HttpServlet{
	
	private static final long serialVersionUID = 6L;
	
	public DeleteUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("users.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//checking is user has admin rights otherwise send to login page
		if (!User.isSessionAdmin(request)) {
			RequestDispatcher req = request.getRequestDispatcher("../login.jsp");
			request.setAttribute("error", "Incorrect credentials");
			req.include(request, response);
			return;
		}
		
		//servlet gets information about user (ID) from jsp page and delete it 
		//and booking of this user from DB using userDAO.delete and bookingDAO.deleteAllUser \
		
		Long user_id = Long.parseLong(request.getParameter("userid"));
		UserDAO userDAO = new UserDAO();
		BookingDAO bookingDAO = new BookingDAO();

		try {
			userDAO.delete(user_id);
			bookingDAO.deleteAllUser(user_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Could not delete user");
		}
			
		RequestDispatcher req = request.getRequestDispatcher("users.jsp");
		request.setAttribute("success", "User was successfully deteted");
		req.include(request, response); 
		
	}


}

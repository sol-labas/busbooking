package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import angela.kuznetsova.assignment2.Booking;
import angela.kuznetsova.assignment2.BookingDAO;
import angela.kuznetsova.assignment2.User;

public class DeleteBookingUser extends HttpServlet{
	
	private static final long serialVersionUID = 8L;
	
	public DeleteBookingUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("account.jsp"); //redirection to correct jsp page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//checking is real user and has user role
		
		if (!User.isSessionUser(request)) {
			RequestDispatcher req = request.getRequestDispatcher("../login.jsp");
			request.setAttribute("error", "Incorrect credentials");
			req.include(request, response);
			return;
		}
		
		//servlet gets information about booking (ID and user ID) from jsp page and delete it from DB using bookingDAO.delete
		
		HttpSession session = request.getSession();
		Long user_id = (long)session.getAttribute("id");
		Long booking_id = Long.parseLong(request.getParameter("bookingid"));
		BookingDAO bookingDAO = new BookingDAO();
		
		
		try {

			bookingDAO.deleteByUser(booking_id, user_id); 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Could not delete booking");
		}
			
		RequestDispatcher req = request.getRequestDispatcher("account.jsp");
		request.setAttribute("successDelete", "Booking was successfully deteted");
		req.include(request, response); 
		//response.sendRedirect("bookings.jsp");	
	}


}

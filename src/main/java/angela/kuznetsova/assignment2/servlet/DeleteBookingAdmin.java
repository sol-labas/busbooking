package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angela.kuznetsova.assignment2.User;
import angela.kuznetsova.assignment2.BookingDAO;

public class DeleteBookingAdmin extends HttpServlet{
	
	private static final long serialVersionUID = 7L;
	
	public DeleteBookingAdmin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("booking.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//checking is user has admin rights 
		
		if (!User.isSessionAdmin(request)) {
			RequestDispatcher req = request.getRequestDispatcher("../login.jsp");
			request.setAttribute("error", "Incorrect credentials");
			req.include(request, response);
			return;
		}
		
		//servlet gets information about booking (ID) from jsp page and delete it from DB using bookingDAO.delete
		
		Long booking_id = Long.parseLong(request.getParameter("bookingid"));
		BookingDAO bookingDAO = new BookingDAO();

		try {
			bookingDAO.delete(booking_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Could not delete booking");
		}
			
		RequestDispatcher req = request.getRequestDispatcher("bookings.jsp");
		request.setAttribute("success", "Booking was successfully deteted");
		req.include(request, response);
		//response.sendRedirect("bookings.jsp");	
	}


}

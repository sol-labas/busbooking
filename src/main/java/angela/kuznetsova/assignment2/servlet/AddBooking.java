package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import angela.kuznetsova.assignment2.Booking;
import angela.kuznetsova.assignment2.BookingDAO;
import angela.kuznetsova.assignment2.Route;
import angela.kuznetsova.assignment2.RouteDAO;
import angela.kuznetsova.assignment2.User;

public class AddBooking extends HttpServlet {

	private static final long serialVersionUID = 5L;

	public AddBooking() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("account.jsp"); //redirection to correct jsp page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//checking is user has admin rights otherwise send to login page 
		
		if (!User.isSessionUser(request)) {
			RequestDispatcher req = request.getRequestDispatcher("../login.jsp");
			request.setAttribute("error", "Incorrect credentials");
			req.include(request, response);
			return;
		}

		//servlet gets information about booking from jsp page and sent it to DB using bookingDAO.insert
		
		try {
			HttpSession session = request.getSession();
			Long user_id = (long) session.getAttribute("id");
			Long route_id = Long.parseLong(request.getParameter("routeid"));
			int number_places = Integer.parseInt(request.getParameter("number_places"));

			RouteDAO routeDAO = new RouteDAO();
			Route route = routeDAO.getRoutebyId(Long.parseLong(request.getParameter("routeid")));

			double price = number_places * route.getPricePerSeat();

			Booking newBooking = new Booking(0, user_id, route_id, number_places, price);
			BookingDAO bookingDAO = new BookingDAO();

			bookingDAO.insert(newBooking);

		} catch (NumberFormatException | SQLException e) {

			e.printStackTrace();
		}

		RequestDispatcher req = request.getRequestDispatcher("account.jsp");
		request.setAttribute("successAdd", "Booking was successfully added");
		req.include(request, response);
	}

}

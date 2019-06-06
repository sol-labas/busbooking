package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import angela.kuznetsova.assignment2.BookingDAO;
import angela.kuznetsova.assignment2.RouteDAO;
import angela.kuznetsova.assignment2.User;

public class DeleteRoute extends HttpServlet{
	
	private static final long serialVersionUID = 9L;
	
	public DeleteRoute() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("routes.jsp"); //redirection to correct jsp page
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
		
		//servlet gets information about route (ID) from jsp page and delete it 
		//and booking of its route from DB using routeDAO.delete and bookingDAO.deleteAllRoute
		
		Long route_id = Long.parseLong(request.getParameter("routeid"));
		RouteDAO routeDAO = new RouteDAO();
		BookingDAO bookingDAO = new BookingDAO();

		try {
			routeDAO.delete(route_id);
			bookingDAO.deleteAllRoute(route_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Could not delete route");
		}
			
		RequestDispatcher req = request.getRequestDispatcher("routes.jsp");
		request.setAttribute("successDelete", "Route was successfully deteted");
		req.include(request, response);
			
	}
}


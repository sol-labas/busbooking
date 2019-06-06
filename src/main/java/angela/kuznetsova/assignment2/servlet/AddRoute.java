package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import angela.kuznetsova.assignment2.Route;
import angela.kuznetsova.assignment2.RouteDAO;
import angela.kuznetsova.assignment2.User;
import angela.kuznetsova.assignment2.UserDAO;

public class AddRoute extends HttpServlet {

	private static final long serialVersionUID = 4L;

	public AddRoute() {
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
		
		//servlet gets information about route from jsp page and sent it to DB using routeDAO.insert
		
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		int number_places = Integer.parseInt(request.getParameter("number_places"));
		double price = Double.parseDouble(request.getParameter("price"));
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date depart_time = new Date();
		try {
			depart_time = formatter1.parse(request.getParameter("depart_time"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Route newRoute = new Route(0, source, destination, number_places, price, depart_time);
		RouteDAO routeDAO = new RouteDAO();

		try {
			routeDAO.insert(newRoute);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Could not save route");
		}
		RequestDispatcher req = request.getRequestDispatcher("routes.jsp");
		request.setAttribute("successAdd", "Route was successfully added");
		req.include(request, response);
	}

}

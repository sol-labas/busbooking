package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {

	private static final long serialVersionUID = 3L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//canceling current session
		
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("Logged out");
		response.sendRedirect("index.jsp"); //rediraction to main page
		
	}

}

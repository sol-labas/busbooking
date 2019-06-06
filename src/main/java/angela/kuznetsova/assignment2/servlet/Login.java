package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import angela.kuznetsova.assignment2.User;
import angela.kuznetsova.assignment2.UserDAO;

public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Login() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("login.jsp"); //redirection to correct jsp page
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//checking credential and user role
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO newUserDAO = new UserDAO();
		
		try {
			User user = newUserDAO.getUserByLogin(username);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					HttpSession session=request.getSession();
					session.setAttribute("role", user.getRole()); //set role
					session.setAttribute("id", user.getId()); //set user id 
			        
					if(user.getRole().equals(User.ADMIN)) {
						System.out.println("Logged in as admin");
						response.sendRedirect("admin/index.jsp"); //redirection to admin page if admin
					}
					if(user.getRole().equals(User.USER)) {
						System.out.println("Logged in as user");
						response.sendRedirect("user/index.jsp"); //redirection to user page if user
					}
				} else {
					System.out.println("Wrong password");
				}
			} else {
				System.out.println("User not found");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher req = request.getRequestDispatcher("login.jsp");
		request.setAttribute("error", "Incorrect credentials"); //show error
		req.include(request, response);
	}
 

}

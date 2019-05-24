package angela.kuznetsova.assignment2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Route extends HttpServlet {

	private static final long serialVersionUID = 2L; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		           
	        // Setting up the content type of web page      
	        response.setContentType("text/html");
	        // Writing the message on the web page      
	        PrintWriter out = response.getWriter();      
	        out.println("<h1>" + "txt" + "</h1>");      
	        out.println("<p>" + "Hello Friends!" + "</p>");   
	    
	}

}

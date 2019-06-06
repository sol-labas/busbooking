package angela.kuznetsova.assignment2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class User {
	
	//fields
	public static final String USER = "user";
	public static final String ADMIN = "admin";
	
	private long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String role;
	
	//getters and setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	//constractor
	public User(long id, String firstName, String lastName, String username, String password, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", role=" + role + "]";
	}
	
	//checking the user role is admin
	public static boolean isSessionAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		return (session.getAttribute("role") != null && session.getAttribute("role").equals("admin"));
			 
	}
	
	//checking the user role is user
	
	public static boolean isSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		return (session.getAttribute("role") != null && session.getAttribute("role").equals("user"));
			 
	}



}

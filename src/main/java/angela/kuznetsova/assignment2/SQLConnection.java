package angela.kuznetsova.assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

	static final String dbUrl = "jdbc:mysql://localhost:3306/bus_booking";
	static final String uname = "root";
	static final String password = "";
	
	//establishing connection

	public static Connection get() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(dbUrl, uname, password);
	}
	
	
}

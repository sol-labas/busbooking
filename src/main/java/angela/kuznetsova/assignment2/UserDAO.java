package angela.kuznetsova.assignment2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

	public void insert(User user) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "insert into user (login, password, first_name, last_name, role) values (?, ?, ?, ?, ?)";

		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getFirstName());
		stmt.setString(4, user.getLastName());
		stmt.setString(5, user.getRole());
		stmt.executeUpdate();

		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Failed to get user id");
			}
		}
		stmt.close();
		conn.close();
	}
	
	public User getUserByLogin (String username) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "select id, login, password, first_name, last_name, role from user where login = ? ;";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		
		User user = null;

		while (rs.next()) {

			Long id = rs.getLong("id");
			String login = rs.getString("login");
			String password = rs.getString("password");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String role = rs.getString("role");
			
			user = new User(id, first_name, last_name, login, password, role);
		}
		
		
		stmt.close();
		conn.close();
			
		return user;
		
		
	}

}

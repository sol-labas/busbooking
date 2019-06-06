package angela.kuznetsova.assignment2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RouteDAO {

	//insert in DB data about route
	
	public void insert(Route route) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "insert into route (source, destination, number_places, price, depart_time) values (?, ?, ?, ?, ?)";

		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, route.getSource());
		stmt.setString(2, route.getDestination());
		stmt.setInt(3, route.getNumberOfSeats());
		stmt.setDouble(4, route.getPricePerSeat());
		stmt.setTimestamp(5, new java.sql.Timestamp(route.getDate().getTime()));
		stmt.executeUpdate();

		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				route.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Failed to get route id");
			}
		}
		stmt.close();
		conn.close();
	}

	//get information about all routes
	
	public List<Route> getAll() throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "select * from route";

		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();

		List<Route> routes = new ArrayList<Route>();

		while (rs.next()) {

			Long id = rs.getLong("id");
			String source = rs.getString("source");
			String destination = rs.getString("destination");
			int number_places = rs.getInt("number_places");
			double price = rs.getDouble("price");
			Date depart_time = rs.getTimestamp("depart_time");

			routes.add(new Route(id, source, destination, number_places, price, depart_time));
		}

		stmt.close();
		conn.close();

		return routes;

	}

	//get specific route by ID 
	public Route getRoutebyId(long routeId) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "select * from route where id = ?";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setLong(1, routeId);
		ResultSet rs = stmt.executeQuery();

		Route route = null;

		while (rs.next()) {

			Long id = rs.getLong("id");
			String source = rs.getString("source");
			String destination = rs.getString("destination");
			int number_places = rs.getInt("number_places");
			double price = rs.getDouble("price");
			Date depart_time = rs.getTimestamp("depart_time");

			route = new Route(id, source, destination, number_places, price, depart_time);
		}

		stmt.close();
		conn.close();

		return route;

	}
	
	//delete specific route by ID
	
	public void delete(long routeId) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "delete from route where id = ?";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setLong(1, routeId);
		stmt.executeUpdate();

		stmt.close();
		conn.close();
	}

}

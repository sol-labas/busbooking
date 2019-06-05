package angela.kuznetsova.assignment2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingDAO {
	
	public void insert(Booking booking) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "insert into booking (route_id, number_places, price, user_id) values (?, ?, ?, ?)";

		PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		stmt.setLong(1, booking.getRouteId());
		stmt.setInt(2, booking.getNumberOfSeats());
		stmt.setDouble(3, booking.getPrice());
		stmt.setLong(4, booking.getUserId());
		stmt.executeUpdate();

		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				booking.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Failed to get booking id");
			}
		}
		stmt.close();
		conn.close();
	}
	
	public List<Booking> getAll () throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "select * from booking";

		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		
		List<Booking> bookings = new ArrayList<Booking>();

		while (rs.next()) {

			Long id = rs.getLong("id");
			Long user_id = rs.getLong("user_id");
			Long route_id = rs.getLong("route_id");
			int number_places = rs.getInt("number_places");
			double price = rs.getDouble("price");
			
			bookings.add(new Booking(id, user_id, route_id, number_places, price));
		}
		
		
		stmt.close();
		conn.close();
			
		return bookings;
			
	}
	
	public List<Booking> getByUserID (long userid) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "select * from booking where user_id = ?";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setLong(1, userid);
		ResultSet rs = stmt.executeQuery();
		
		List<Booking> bookings = new ArrayList<Booking>();

		while (rs.next()) {

			Long id = rs.getLong("id");
			Long user_id = rs.getLong("user_id");
			Long route_id = rs.getLong("route_id");
			int number_places = rs.getInt("number_places");
			double price = rs.getDouble("price");
			
			bookings.add(new Booking(id, user_id, route_id, number_places, price));
		}
		
		
		stmt.close();
		conn.close();
			
		return bookings;
			
	}
	
	
	public void delete(long bookingId) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "delete from booking where id = ?";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setLong(1, bookingId);
		stmt.executeUpdate();

		stmt.close();
		conn.close();
	}
	
	public void deleteByUser(long bookingId, long userId) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "delete from booking where user_id = ? and id = ?";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setLong(1, userId);
		stmt.setLong(2, bookingId);
		stmt.executeUpdate();

		stmt.close();
		conn.close();
	}
	
	public void deleteAllUser(long userId) throws SQLException {
		Connection conn = SQLConnection.get();

		String query = "delete from booking where user_id = ?";

		PreparedStatement stmt = conn.prepareStatement(query);

		stmt.setLong(1, userId);
		stmt.executeUpdate();

		stmt.close();
		conn.close();
	}
}

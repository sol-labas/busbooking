package angela.kuznetsova.assignment2;

public class Booking {
	
	//fields
	private long id;
	private long userId;
	private long routeId;
	private int numberOfSeats;
	private double price;
	
	//getters and setters
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getRouteId() {
		return routeId;
	}
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", userId=" + userId + ", routeId=" + routeId + ", numberOfSeats=" + numberOfSeats
				+ ", price=" + price + "]";
	}
	
	//constractor
	public Booking(long id, long userId, long routeId, int numberOfSeats, double price) {
		super();
		this.id = id;
		this.userId = userId;
		this.routeId = routeId;
		this.numberOfSeats = numberOfSeats;
		this.price = price;
	}
	


}

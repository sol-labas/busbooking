package angela.kuznetsova.assignment2;

public class Booking {
	private User user;
	private Route route;
	private int numberOfSeats;
	private double price;
	
	 
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
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
	@Override
	public String toString() {
		return "Booking [user=" + user + ", route=" + route + ", numberOfSeats=" + numberOfSeats + ", price=" + price
				+ "]";
	}
	public Booking(User user, Route route, int numberOfSeats, double price) {
		super();
		this.user = user;
		this.route = route;
		this.numberOfSeats = numberOfSeats;
		this.price = price;
	}
	
	


}

package angela.kuznetsova.assignment2;

import java.util.Date;

public class Route {
	private String source;
	private String destination;
	private int numberOfSeats;
	private double pricePerSeat;
	private Date date;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public double getPricePerSeat() {
		return pricePerSeat;
	}
	public void setPricePerSeat(double pricePerSeat) {
		this.pricePerSeat = pricePerSeat;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Route [source=" + source + ", destination=" + destination + ", numberOfSeats=" + numberOfSeats
				+ ", pricePerSeat=" + pricePerSeat + ", date=" + date + "]";
	}
	public Route(String source, String destination, int numberOfSeats, double pricePerSeat, Date date) {
		super();
		this.source = source;
		this.destination = destination;
		this.numberOfSeats = numberOfSeats;
		this.pricePerSeat = pricePerSeat;
		this.date = date;
	}
	
	
	

	
}
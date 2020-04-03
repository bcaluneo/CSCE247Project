
public class Payment {
	private User user;
	private Theater venue;
	private Room room;
	private Movie movie;
	char seatRow;
	int seatColumn;
	
	public Payement(User user, Theater Venue, Movie movie, Room room) {
		this.user = user;
		this.venue = venue;
		this.movie = movie;
		this.room = room;
	}
	
	public void initiatePayment() {
		
	}
	
	public void cancelPayment() {
		
	}
}
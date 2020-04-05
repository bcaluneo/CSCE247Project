
public class Payment {
	private User user;
	private Venue venue;
	private Room room;
	private Show show;
	private Seat seat
	
	public Payment(User user, Venue venue, Show show, Room room, Seat seat) {
		this.user = user;
		this.venue = venue;
		this.show = show;
		this.room = room;
		this.seat = seat;
	}
	
	public void initiatePayment() {
		
	}
	
	public void cancelPayment() {
		
	}
}
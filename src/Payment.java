
public class Payment {
	private User user;
	private Venue venue;
	private Theater theater;
	private Show show;
	private Seat seat;

	public Payment(User user, Venue venue, Theater theater, Show show, Seat seat) {
		this.user = user;
		this.venue = venue;
		this.theater = theater;
		this.show = show;
		this.seat = seat;
	}

	public void initiatePayment() {

	}

	public void cancelPayment() {

	}
}

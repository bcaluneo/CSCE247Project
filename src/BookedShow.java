public class BookedShow {

	private Show show;
	private Venue venue;
	private Seat[] seat;

	public BookedShow(Show show, Venue venue, Seat[] seat) {
		this.show = show;
		this.venue = venue;
		this.seat = seat;
	}

	public void printTicket() {
	}

	public void emailTicket() {
	}
}

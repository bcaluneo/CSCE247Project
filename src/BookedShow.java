import java.util.List;

public class BookedShow {

	private Show show;
	private Venue venue;
	private List<Seat> seats;
	private Theater theater;

	public BookedShow(Venue venue, Theater theater, Show show, List<Seat> seats) {
		this.show = show;
		this.venue = venue;
		this.seats = seats;
		this.theater = theater;
	}

	public void printTicket() {
	}

	public void emailTicket() {
	}
}

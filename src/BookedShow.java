import java.io.PrintWriter;
import java.util.List;
import java.io.IOException;
import java.io.File;

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

	public Show getShow() {
		return show;
	}

	public Venue getVenue() {
		return venue;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public Theater getTheater() {
		return theater;
	}

	public void printTicket() {
		try {
			PrintWriter pw = new PrintWriter(new File(show.getShowInformation("name") + " ticket stub.txt"));
			pw.println("**********************");
			pw.println("Venue: " + venue.getName());
			pw.println("Theater: " + theater.getTheaterNumber());
			pw.println("Seats: ");
			for (Seat s : seats) {
				pw.println("" + s.getSeatRow() + "" + s.getSeatColumn());
			}
			pw.println("**********************");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void emailTicket() {
	}
}

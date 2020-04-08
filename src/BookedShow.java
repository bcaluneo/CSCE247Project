import java.io.PrintWriter;
import java.util.List;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author Brendan Caluneo
 * this class keeps track of all the booked shows or movies in a list by a user
 */
public class BookedShow {

	private Show show;
	private Venue venue;
	private List<Seat> seats;
	private Theater theater;

	/**
	 * constructor
	 * @param venue
	 * @param theater
	 * @param show
	 * @param seats
	 */
	public BookedShow(Venue venue, Theater theater, Show show, List<Seat> seats) {
		this.show = show;
		this.venue = venue;
		this.seats = seats;
		this.theater = theater;
	}

	/**
	 * getters
	 * returns each variable
	 */
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

	/**
	 * this method prints out a ticket which is booked in a text file
	 */
	public void printTicket() {
		try {
			PrintWriter pw = new PrintWriter(new File(show.getShowInformation("name") + " ticket stub.txt"));
			pw.println("**********************");
			pw.println("Venue: " + venue.getName());
			pw.println("Theater: " + theater.getTheaterNumber());
			pw.println("Total Price: " + (Double.parseDouble(""+show.getShowInformation("price"))*seats.size()));
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

	/**
	 * this method sends the ticket virtually to user email given
	 */
	public void emailTicket() {
	}
}

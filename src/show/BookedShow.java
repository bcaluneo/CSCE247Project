package show;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import venue.Seat;
import venue.Theater;
import venue.Venue;

/**
 * Maintains which shows the user has booked tickets to.
 * 
 * @author Team Blue
 */
public class BookedShow {

	private Show show;
	private Venue venue;
	private List<Seat> seats;
	private Theater theater;
	private String time;

	/**
	 * Creates a BookedShow
	 *
	 * @param venue
	 * @param theater
	 * @param show
	 * @param seats
	 * @param time
	 */
	public BookedShow(Venue venue, Theater theater, Show show, List<Seat> seats, String time) {
		this.show = show;
		this.venue = venue;
		this.seats = seats;
		this.time = time;
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

	public String getTime() {
		return this.time;
	}

	/**
	 * Prints out a ticket for a booked show.
	 */
	public void printTicket() {
		if (show != null) {
			try {
				PrintWriter pw = new PrintWriter(new File(show.getShowInformation("name") + " ticket stub.txt"));
				pw.println("**********************");
				pw.println("Venue: " + venue.getName());
				pw.println("Theater: " + theater.getTheaterNumber());
				pw.println("Total Price: " + (Double.parseDouble("" + show.getShowInformation("price")) * seats.size()));
				pw.println("Time: " + time);
				pw.println("Seats: ");
				for (Seat s : seats) {
					pw.println("\t" + s.getSeatRow() + "" + s.getSeatColumn());
				}
				pw.println("**********************");
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * this method sends the ticket virtually to user email given
	 */
	public void emailTicket() {
	}
}

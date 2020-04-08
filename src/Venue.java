/**
 * A venue that contains a list of theaters.
 * @author Team Blue
 *
 */
public class Venue {

	/** The maximum amount of theaters. */
	private static final int MAX_THEATERS = 10;

	private String name, location;
	private Review review;
	private Theater[] theaters;

	/**
	 * Creates a theater instance with the given name and location.
	 * @param name
	 * @param location
	 */
	public Venue(String name, String location) {
		this.name = name;
		this.location = location;
		theaters = new Theater[MAX_THEATERS];

		for (int i = 0; i < 5; i++) {
			theaters[i] = new Theater(i, 'E', 5);
		}
	}

	/**
	 * Books a seat within a theater.
	 * @param theater
	 * @param row
	 * @param column
	 */
	public void bookSeat(Theater theater, char row, int column) {
		theater.getSeats()[row-'A'][column].setBooked(true);
	}

	/**
	 * Refunds the seat to the user that booked it.
	 */
	public void refundSeat() {
	}

	/**
	 * Adds a theater to the venue's list of theaters.
	 * @param theater
	 */
	public void addTheater(Theater[] theater) {
	}

	/**
	 * Removes a theater from the venue's list of theaters.
	 * @param theater
	 */
	public void deleteTheater(Theater[] theater) {
	}

	public String toString() {
		String printout = "";
		printout += "Name: " + name + "\n";
		printout += "Location: " + location + "\n";
		for (int i = 0; i < theaters.length; i++) {
			if(theaters[i]!=null)
				printout += "Theater: " + i + "\n";
		}
		return printout;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Theater getTheater(int i ) {
		return theaters[i];
	}
}

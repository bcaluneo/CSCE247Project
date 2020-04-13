package venue;

/**
 * A theater within a venue.
 * 
 * @author Team Blue
 *
 */
public class Theater {

	/** A matrix of seats within the theater. */
	private Seat[][] seats;

	/** The theater number. */
	private int theaterNumber;

	/**
	 * Creates a theater with a matrix of seats. Maxrows corresponds to the
	 * character you want the rows to end at.
	 * 
	 * @param theaterNumber
	 * @param maxrows
	 * @param maxcolumns
	 */
	public Theater(int theaterNumber, char maxrows, int maxcolumns) {
		this.theaterNumber = theaterNumber;
		seats = new Seat[maxrows - 'A' + 1][maxcolumns];
		for (int rows = 0; rows < seats.length; rows++)
			for (int columns = 0; columns < seats[0].length; columns++)
				seats[rows][columns] = new Seat((char) ('A' + rows), columns);
	}

	/**
	 * Prints the matrix of seats.
	 */
	public void printSeats() {
		int rows, columns;
		System.out.print("Unbooked Seat: U\n" + "Unbooked Handicapped Seat: H\n" + "Booked Seat: B\n");
		System.out.print("R\\C");
		for (columns = 0; columns < seats[0].length; columns++)
			System.out.print("  " + (columns) + " ");
		System.out.println();
		for (rows = 0; rows < seats.length; rows++) {
			System.out.print("   -");
			for (columns = 0; columns < seats[0].length; columns++)
				System.out.print("----");
			System.out.println();
			System.out.print(" " + (char) ('A' + rows) + " ");
			System.out.print("|");
			for (columns = 0; columns < seats[0].length; columns++) {
				System.out.print(" ");
				if (seats[rows][columns].isBooked()) {
					System.out.print("B");
				} else if (seats[rows][columns].isHandicapped()) {
					System.out.print("H");
				} else {
					System.out.print("U");
				}
				System.out.print(" |");
			}
			System.out.println();
		}
		System.out.print("   -");
		for (columns = 0; columns < seats[0].length; columns++)
			System.out.print("----");
		System.out.println();
	}

	/**
	 * Determines if the matrix of seats is only handicapped.
	 * @return
	 */
	public boolean onlyHandicapped() {
		for (int rows = 0; rows < seats.length; rows++)
			for (int columns = 0; columns < seats[0].length; columns++)
				if (!seats[rows][columns].isHandicapped() && !seats[rows][columns].isBooked()) return false;
		return true;
	}

	/**
	 * Determines if the matrix of seats is full.
	 * @return
	 */
	public boolean isFull() {
		for (int rows = 0; rows < seats.length; rows++)
			for (int columns = 0; columns < seats[0].length; columns++)
				if (!seats[rows][columns].isBooked()) return false;
		return true;
	}

	public Seat[][] getSeats() {
		return seats;
	}

	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}

	public int getTheaterNumber() {
		return this.theaterNumber;
	}
}

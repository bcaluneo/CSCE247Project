package venue;

/**
 * A seat class for use in a theater.
 * 
 * @author Team Blue
 *
 */
public class Seat {
	private char seatRow;
	private int seatColumn;
	private boolean isHandicapped, isBooked;
	
	/**
	 * Creates a seat with a row and column.
	 * @param seatRow
	 * @param seatColumn
	 */
	public Seat(char seatRow, int seatColumn) {
		this.seatRow = seatRow;
		this.seatColumn = seatColumn;
		this.isHandicapped = this.isBooked = false;
	}

	public char getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(char seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}

	public boolean isHandicapped() {
		return isHandicapped;
	}

	public void setHandicapped(boolean isHandicapped) {
		this.isHandicapped = isHandicapped;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
}
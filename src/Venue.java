public class Venue {

	private static final int MAX_THEATERS = 10;

	private String name, location;
	private Review review;
	private Theater[] theaters;

	public Venue(String name, String location) {
		this.name = name;
		this.location = location;
		theaters = new Theater[MAX_THEATERS];

		for (int i = 0; i < 5; i++) {
			theaters[i] = new Theater(i, 'E', 5);
		}
	}

	public void bookSeat(Theater theater, char row, int column) {
		theater.getSeats()[row-'A'][column].setBooked(true);
	}

	public void refundSeat() {
	}

	public void addTheater(Theater[] theater) {
	}

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

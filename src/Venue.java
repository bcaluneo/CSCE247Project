public class Venue {

	private static final int MAX_THEATERS = 10;

	private String name, location;
	private Review review;
	private Theater[] theaters;

	public Venue(String name, String location) {
		this.name = name;
		this.location = location;
		theaters = new Theater[MAX_THEATERS];
	}

	public void bookSeat() {
	}

	public void refundSeat() {
	}

	public void addTheater() {
	}

	public void deleteTheater() {
	}

	public String toString() {
		String printout = "";
		printout += name+"\n";
		return printout;
	}
}

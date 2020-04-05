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
			theaters[i] = new Theater((char)5, 5);
		}
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
		for (int i = 0; i < theaters.length; i++) {
			if(theaters[i]!=null)
				printout += "Theater: " + i + "\n";
		}
		return printout;
	}

	public String getName() {
		return this.name;
	}
}

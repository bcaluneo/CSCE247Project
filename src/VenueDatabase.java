import java.util.List;
import java.util.ArrayList;

public class VenueDatabase {
	private static VenueDatabase venueDatabase;
	private List<Venue> venues;

	private VenueDatabase() {
		venues = new ArrayList<Venue>();
		for (int i = 0; i < 5; i++) {
			venues.add(new Venue("Venue " + i, "Location " + i));
		}
	}

	public static VenueDatabase getInstance() {
		if (venueDatabase == null) {
			venueDatabase = new VenueDatabase();
		}

		return venueDatabase;
	}

	public Venue getVenueByIndex(int index) {
		return venues.get(index);
	}

	public Venue getVenueByName(String name) {
		for (Venue venue : venues) {
			if (venue.getName().equals(name)) return venue;
		}

		return null;
	}

	public void updateVenue(Venue venue, int index) {
		venues.set(index, venue);
	}

	public void addVenue(Venue venue) {
		venues.add(venue);
	}

	public void removeVenue(Venue venue) {
		venues.remove(venue);
	}

	public String toString() {
		String printout = "";
		printout += "===========================\n";
		printout += "Venues: \n";
		for (int i = 0; i < venues.size(); i++) {
			if (venues.get(i) != null) printout += i + ": " + venues.get(i).getName() + "\n";
		}

		return printout;
	}
}

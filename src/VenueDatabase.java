import java.util.List;

/**
 * Maintains a list of all the venue's in the program.
 * @author Team Blue
 *
 */
public class VenueDatabase {
	
	private static VenueDatabase venueDatabase;
	
	/** A list of all venues. */
	private List<Venue> venues;

	/**
	 * Initializes the Venue Database.
	 */
	private VenueDatabase() {
		venues = DataLoader.loadVenues();
	}

	public static VenueDatabase getInstance() {
		if (venueDatabase == null) {
			venueDatabase = new VenueDatabase();
		}

		return venueDatabase;
	}

	/**
	 * Returns a venue using an index from the list.
	 * @param index
	 * @return
	 */
	public Venue getVenueByIndex(int index) {
		return venues.get(index);
	}

	/**
	 * Returns a venue if the given name is found within the list.
	 * @param name
	 * @return
	 */
	public Venue getVenueByName(String name) {
		for (Venue venue : venues) {
			if (venue.getName().equals(name)) return venue;
		}

		return null;
	}

	/**
	 * Updates a venue's information.
	 * @param venue
	 * @param index
	 */
	public void updateVenue(Venue venue, int index) {
		venues.set(index, venue);
	}

	/**
	 * Adds a venue to the list.
	 * @param venue
	 */
	public void addVenue(Venue venue) {
		venues.add(venue);
	}

	/**
	 * Removes a venue from the list.
	 * @param venue
	 */
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
	
	public List<Venue> getVenues() {
		return this.venues;
	}
}

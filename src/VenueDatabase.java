public class VenueDatabase {
  private static VenueDatabase venueDatabase;
	private Venue[] venues;

	private VenueDatabase() {
		venues = new Venue[100];
	}

	public static VenueDatabase getInstance() {
		if (venueDatabase == null) {
			venueDatabase  = new VenueDatabase();
		}

		return venueDatabase ;
	}

  public Venue getVenueByname(String name) {
    return null;
  }

  public void addVenue(Venue venue) {
  }

  public void removeVenue(Venue venue) {
  }

  public void listVenues() {
  }
}

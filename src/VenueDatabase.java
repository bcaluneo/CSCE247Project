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

		return venueDatabase;
	}

  public Venue getVenueByname(String name) {
    return null;
  }

  public void addVenue(Venue venue) {
  }

  public void removeVenue(Venue venue) {
  }

  public void listVenues() {
    System.out.println("===========================");
    System.out.println("Venues: ");
    for (int i = 0; i < venues.length; i++) {
				String printout = venues[i];
				System.out.println(i + ": " + venues);
		}
  }
}

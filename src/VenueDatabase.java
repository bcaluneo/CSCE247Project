public class VenueDatabase {
  private static VenueDatabase venueDatabase;
	private Venue[] venues;

	private VenueDatabase() {
		venues = new Venue[100];
    for (int i = 0; i < 5; i++) {
      venues[i] = new Venue("Venue " + i, "Location " + i);
    }
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

  public String toString() {
    String printout = "";
    printout += "===========================\n";
    printout += "Venues: \n";
    for (int i = 0; i < venues.length; i++) {
    	if(venues[i]!=null)
    		printout += i + ": " + venues[i].toString();
		}

    return printout;
  }
}

public class Staff extends User {

	public void editVenueInformation(Venue venue, int index) {
		if (VenueDatabase.getInstance().getVenueByName(venue.getName) == null) return;
		VenueDatabase.getInstance().updateVenue(venue, index);
	}

	public void addVenue(Venue venue) {
		VenueDatabase.getInstance().addVenue(venue);
	}

	public void removeVenue(Venue venue) {
		if (VenueDatabase.getInstance().getVenueByName(venue.getName) == null) return;
		VenueDatabase.getInstance().removeVenue(venue);
	}
}

package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import managers.VenueDatabase;
import venue.Venue;

class VenueDatabaseTest {

	@Test
	void testGetVenue() {
		VenueDatabase vd = VenueDatabase.getInstance();
		vd.getVenues().clear();
		Venue venue = new Venue("Test Venue", "Test Location");
		vd.addVenue(venue);

		assertEquals(true, vd.getVenueByIndex(0) != null);
		assertEquals(true, vd.getVenueByName("Test Venue") != null);
	}

	@Test
	void testAddVenue() {
		VenueDatabase vd = VenueDatabase.getInstance();
		vd.getVenues().clear();
		Venue venue = new Venue("Test Venue", "Test Location");
		vd.addVenue(venue);
		assertEquals(true, vd.getVenueByName("Test Venue") != null);
	}

	@Test
	void testRemoveVenue() {
		VenueDatabase vd = VenueDatabase.getInstance();
		vd.getVenues().clear();
		Venue venue = new Venue("Test Venue 2", "Test Location");
		vd.addVenue(venue);
		vd.removeVenue(venue);
		assertEquals(true, vd.getVenueByName("Test Venue 2") == null);
	}

	@Test
	void testToString() {
		VenueDatabase vd = VenueDatabase.getInstance();
		vd.getVenues().clear();
		Venue venue = new Venue("Test Venue 2", "Test Location");
		vd.addVenue(venue);

		String toString = vd.toString();
		assertEquals(true, toString != null);

	}
}

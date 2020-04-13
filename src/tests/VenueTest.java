package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import venue.Seat;
import venue.Theater;
import venue.Venue;

class VenueTest {

	@Test
	void testSeat() {
		Seat seat = new Seat('A', 0);

		assertEquals('A', seat.getSeatRow());
		assertEquals(0, seat.getSeatColumn());

		seat.setBooked(false);
		seat.setHandicapped(true);

		assertEquals(false, seat.isBooked());
		assertEquals(true, seat.isHandicapped());

		seat.setSeatColumn(2);
		seat.setSeatRow('C');

		assertEquals('C', seat.getSeatRow());
		assertEquals(2, seat.getSeatColumn());
	}

	@Test
	void testTheater() {
		Theater theater = new Theater(0, 'E', 5);

		assertEquals(0, theater.getTheaterNumber());

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				theater.getSeats()[i][j].setHandicapped(true);
			}
		}

		theater.getSeats()[4][2].setHandicapped(false);

		assertEquals(false, theater.onlyHandicapped());

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				theater.getSeats()[i][j].setBooked(true);
			}
		}

		assertEquals(true, theater.isFull());

		Seat[][] newSeats = new Seat[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newSeats[i][j] = new Seat((char)('A' + i), j);
			}
		}
		
		newSeats[0][2].setHandicapped(true);
		
		theater.setSeats(newSeats);
		
		assertEquals(true, theater.getSeats()[0][2].isHandicapped());
	}
	
	@Test
	void testPrintSeats() {
		Theater theater = new Theater(0, 'E', 5);
		theater.printSeats();
	}
	
	@Test
	void testToString() {
		Venue venue = new Venue("Test", "Test");
		String toString = venue.toString();
		assertEquals(true, toString != null);
	}
	
	@Test
	void testVenue() {
		Venue venue = new Venue("Test Venue", "Test Location");
		assertEquals("Test Venue", venue.getName());
		assertEquals("Test Location", venue.getLocation());
		
		venue.setName("New Name");
		venue.setLocation("New Location");
		assertEquals("New Name", venue.getName());
		assertEquals("New Location", venue.getLocation());
		
		Theater theater = venue.getTheater(3);
		assertEquals(3, theater.getTheaterNumber());
		
		venue.bookSeat(theater, 'A', 2);
		
		assertEquals(true, theater.getSeats()[0][2].isBooked());
	}
}

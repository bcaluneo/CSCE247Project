package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import show.Show;
import user.Child;
import user.User;
import venue.Seat;
import venue.Venue;

class UserTest {

	@Test
	void testInformation() {
		User user = new User();
		user.setProfileInformation("discount", true);

		boolean flag = Boolean.parseBoolean("" + user.getProfileInformation("discount"));
		assertEquals(true, flag);

		user.setPaymentInformation("card-number", 1234);

		int num = Integer.parseInt("" + user.getPaymentInformation("card-number"));
		assertEquals(1234, num);
	}

	@Test
	void testIsDefaultUser() {
		User user = new User();
		user.setProfileInformation("username", "Anonymous");

		assertEquals(true, user.isDefaultUser());
	}

	@Test
	void testIsStaff() {
		User user = new User();
		user.setProfileInformation("username", "Anonymous");

		assertEquals(false, user.isStaff());
		assertEquals(false, user.isAdmin());
	}

	@Test
	void testBookedShow() {
		User user = new User();
		
		Show show = new Show();
		Venue venue = new Venue("Test Venue", "Test Location");
		show.setShowInformation("name", "Test Show");
		show.setShowInformation("price", 12.30);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(new Seat('A', 0));
		user.bookShow(venue, venue.getTheater(0), show, seats, "12:00");

		assertEquals(1, user.getBookedShows().size());
		
		File ticketFile = new File(show.getShowInformation("name") + " ticket stub.txt");
		assertEquals(true, ticketFile.exists());
	}
	
	@Test
	void testChild() {
		User parent = new User();
		Child child = new Child();
		
		child.setParent(parent);
		parent.addChild(child);
		
		assertEquals(true, child.getParent().equals(parent));
		assertEquals(1, parent.getChildren().size());
	}

}

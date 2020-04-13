package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import show.Age;
import show.Genre;
import show.Review;
import show.Show;
import show.ShowType;
import user.User;

class ShowTest {

	@Test
	void testInformation() {
		Show show = new Show();
		show.setShowInformation("name", "Test Show");

		String showName = "" + show.getShowInformation("name");
		assertEquals("Test Show", showName);
	}

	@Test
	void testAverage() {
		Show show = new Show();
		for (int i = 0; i < 15; i++) {
			show.addReview(new Review(null, null, 2));
		}

		int avgRating = (int) show.getAverage();
		assertEquals(2, avgRating); // assertEquals for a double is deprecated?
	}
	
	@Test
	void testToString() {
		Show show = new Show();
		for (int i = 0; i < 15; i++) {
			show.addReview(new Review(new User(), "Desc: " + i, 2));
		}
		
		List<String> times = new ArrayList<String>();
		times.add("12:00");
		
		show.setShowInformation("times", times);
		
		assertEquals(true, show.toString(true) != null);
	}

	@Test
	void testAddReview() {
		Show show = new Show();
		User user = new User();
		user.setProfileInformation("username", "Bob");
		Review review = new Review(user, "Test Description", 1);

		show.addReview(review);

		String description = show.getReviewByAuthor("Bob").getDescription();
		assertEquals("Test Description", description);
	}

	@Test
	void testRemoveReview() {
		Show show = new Show();
		User user = new User();
		user.setProfileInformation("username", "Bob");
		Review review = new Review(user, "Test Description", 1);

		show.addReview(review);
		show.deleteReview(review);

		assertEquals(0, (int) show.getAverage());
	}

	@Test
	void testAge() {
		Show show = new Show();
		show.setShowInformation("age", Age.PG13);

		Age age = (Age) show.getShowInformation("age");
		assertEquals(Age.PG13, age);
	}

	@Test
	void testGenre() {
		Show show = new Show();
		show.setShowInformation("genre", Genre.COMEDY);

		Genre genre = (Genre) show.getShowInformation("genre");
		assertEquals(Genre.COMEDY, genre);
	}

	@Test
	void testShowType() {
		Show show = new Show();
		show.setShowInformation("showType", ShowType.MOVIE);

		ShowType showType = (ShowType) show.getShowInformation("showType");
		assertEquals(ShowType.MOVIE, showType);
	}

	@Test
	void testReviewToString() {
		Show show = new Show();
		User user = new User();
		user.setProfileInformation("username", "Bob");
		Review review = new Review(user, "Test Description", 1);

		show.addReview(review);

		String description = show.getReviewByAuthor("Bob").getDescription();
		assertEquals(true, review.toString().contains(description));
	}
}

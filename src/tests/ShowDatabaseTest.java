package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import managers.ShowDatabase;
import show.Age;
import show.Genre;
import show.Show;

class ShowDatabaseTest {

	@Test
	void testSingleton() {
		ShowDatabase db = ShowDatabase.getInstance();
		assertEquals(true, db != null);
	}

	@Test
	void testAddShow() {
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();
		Show show = new Show();
		show.setShowInformation("name", "Test Show");
		db.addShow(show);

		assertEquals(1, db.getShows().size());
	}

	@Test
	void testRemoveShow() {
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();
		Show show = new Show();
		show.setShowInformation("name", "Test Show");
		db.addShow(show);
		db.removeShow(show);

		assertEquals(0, db.getShows().size());
	}

	@Test
	void testGetShow() {
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();
		Show show = new Show();
		show.setShowInformation("name", "Test Show");
		db.addShow(show);

		assertEquals(show, db.getShowByIndex(0));
		assertEquals(show, db.getShowByName("Test Show"));
	}

	@Test
	void testGenreSort() {
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();

		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			Show show = new Show();
			show.setShowInformation("name", "Test Show " + i);
			show.setShowInformation("genre", Genre.values()[random.nextInt(Genre.values().length)]);
			db.addShow(show);
		}

		assertEquals(true, isEnumListSorted(db.sortByGenre(), Genre.class));
	}

	@Test
	void testAgeSort() {
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();

		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			Show show = new Show();
			show.setShowInformation("name", "Test Show " + i);
			show.setShowInformation("age", Age.values()[random.nextInt(Age.values().length)]);
			db.addShow(show);
		}

		assertEquals(true, isEnumListSorted(db.sortByAge(), Age.class));
	}

	@Test
	void testNameSort() {
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();

		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			Show show = new Show();
			show.setShowInformation("name", (char) ('A' + (char) random.nextInt(3)) + " Test Show");
			
			db.addShow(show);
		}

		List<Show> sorted = db.sortByName();
		boolean isSorted = true;
		for (int i = 0; i < sorted.size(); i++) {
			Show show = sorted.get(i);
			String showName = "" + show.getShowInformation("name");
			if (i + 1 < sorted.size()) {
				String nextName = "" + sorted.get(i + 1).getShowInformation("name");
				if (showName.charAt(0) < nextName.charAt(0)) {
					isSorted = false;
					break;
				}
			}
		}
		
		assertEquals(true, isSorted);
	}
	
	@Test
	void testSearchByName() { 
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();
		
		for (int i = 0; i < 5; i++) {
			Show show = new Show();
			show.setShowInformation("name", "Test Show " + i);
			
			db.addShow(show);
		}
		
		List<Show> shows = db.searchByName("Test Show 2");
		assertEquals(1, shows.size());
	}
	
	@Test
	void testSearchByGenre() { 
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();
		
		for (int i = 0; i < 5; i++) {
			Show show = new Show();
			show.setShowInformation("name", "Test Show " + i);
			show.setShowInformation("genre", Genre.ADVACT);
			
			db.addShow(show);
		}
		
		List<Show> shows = db.searchByGenre(Genre.COMEDY);
		assertEquals(0, shows.size());
	}

	@Test
	void testSearchByAge() { 
		ShowDatabase db = ShowDatabase.getInstance();
		db.getShows().clear();
		
		for (int i = 0; i < 5; i++) {
			Show show = new Show();
			show.setShowInformation("name", "Test Show " + i);
			show.setShowInformation("age", Age.PG13);
			
			db.addShow(show);
		}
		
		List<Show> shows = db.searchByAgeRating(Age.G);
		assertEquals(0, shows.size());
	}

	<E extends Enum<E>> boolean isEnumListSorted(List<Show> shows, Class<E> enumClass) {
		String enumName = enumClass.toString().toLowerCase().substring(11);
		for (int i = 0; i < shows.size(); i++) {
			Show show = shows.get(i);
			Enum e = (Enum) show.getShowInformation(enumName);

			if (i + 1 < shows.size() && shows.get(i + 1) != null) {
				if (e.ordinal() > ((Enum) shows.get(i + 1).getShowInformation(enumName)).ordinal()) {
					return false;
				}
			}
		}

		return true;
	}
}

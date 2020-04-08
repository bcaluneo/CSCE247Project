import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Random;

public class ShowDatabase {

	private static ShowDatabase showDatabase;
	private List<Show> shows;

	private ShowDatabase() {
		shows = new ArrayList<Show>();

		Random random = new Random();

		/* TODO: Test code. */
		for (int i = 0; i < 15; i++) {
			Show show = new Show();
			show.setShowInformation("name", "Show " + i);
			show.setShowInformation("genre", Genre.values()[random.nextInt(Genre.values().length)]);
			shows.add(show);
		}
	}

	public static ShowDatabase getInstance() {
		if (showDatabase == null) {
			showDatabase = new ShowDatabase();
		}

		return showDatabase;
	}

	public Show getShowByIndex(int index) {
		return shows.get(index);
	}

	public Show getShowByName(String showName) {
		for (Show show : shows) {
			String name = ""+show.getShowInformation("name");
			if (name.equals(showName)) {
				return show;
			}
		}

		return null;
	}

	public void addShow(Show show) {
		shows.add(show);
	}

	public void removeShow(Show show) {
		shows.remove(show);
	}

	public List<Show> getShows() {
		return this.shows;
	}

	public String toString() {
		String printout = "";
		printout += "===========================\n";
		printout += "Showings: \n";
		for (int i = 0; i < shows.size(); i++) {
				printout += i + ": " + shows.get(i).toString(false);
		}

		return printout;
	}

	class AgeSort implements Comparator<Show> {
		public int compare(Show a, Show b) {
			Age aAge = (Age) a.getShowInformation("ageRating");
			Age bAge = (Age) b.getShowInformation("ageRating");
			if (aAge.ordinal() > bAge.ordinal()) return 1;
			return -1;
		}
	}

	class GenreSort implements Comparator<Show> {
		public int compare(Show a, Show b) {
			Genre aGenre = (Genre) a.getShowInformation("genre");
			Genre bGenre = (Genre) b.getShowInformation("genre");
			if (aGenre.ordinal() > bGenre.ordinal()) return 1;
			return -1;
		}
	}

	class NameSort implements Comparator<Show> {
		public int compare(Show a, Show b) {
			String aName = "" + a.getShowInformation("name");
			String bName = "" + b.getShowInformation("name");
			if (aName.charAt(0) < bName.charAt(0)) return 1;
			return -1;
		}
	}

	public List<Show> sortByGenre() {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			result.add(show);
		}

		Collections.sort(result, new GenreSort());

		return result;
	}

	public List<Show> sortByName() {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			result.add(show);
		}

		Collections.sort(result, new NameSort());

		return result;
	}

	public List<Show> sortByAgeRating() {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			result.add(show);
		}

		Collections.sort(result, new AgeSort());

		return result;
	}

	public List<Show> searchByName(String name) {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			String showName = "" + show.getShowInformation("name");
			if (showName.contains(name)) {
				result.add(show);
			}
		}

		return result;
	}

	public List<Show> searchByGenre(Genre genre) {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			Genre showGenre = (Genre) show.getShowInformation("genre");
			if (showGenre == genre) {
				result.add(show);
			}
		}

		return result;
	}

	public List<Show> searchByAgeRating(Age age) {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			Age showAge = (Age) show.getShowInformation("ageRating");
			if (showAge == age) {
				result.add(show);
			}
		}

		return result;
	}
}

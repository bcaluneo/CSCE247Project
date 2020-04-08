import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Maintains a list of all the current showings.
 * @author Team Blue
 *
 */
public class ShowDatabase {

	private static ShowDatabase showDatabase;

	/** A list of all shows. */
	private List<Show> shows;

	/**
	 * Initializes the show database.
	 */
	private ShowDatabase() {
		shows = DataLoader.loadShows();
	}

	public static ShowDatabase getInstance() {
		if (showDatabase == null) {
			showDatabase = new ShowDatabase();
		}

		return showDatabase;
	}

	/**
	 * Returns a show using an index from the list.
	 * @param index
	 * @return show
	 */
	public Show getShowByIndex(int index) {
		return shows.get(index);
	}

	/**
	 * Returns a show from the list using the name of the show.
	 * @param showName
	 * @return show
	 */
	public Show getShowByName(String showName) {
		for (Show show : shows) {
			String name = ""+show.getShowInformation("name");
			if (name.equals(showName)) {
				return show;
			}
		}

		return null;
	}

	/**
	 * Adds a show to the list.
	 * @param show
	 */
	public void addShow(Show show) {
		shows.add(show);
	}

	/**
	 * Removes a show from the list.
	 * @param show
	 */
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

	/**
	 * A comparator that sorts showings by age in ascending order.
	 * 
	 * @author Team Blue
	 *
	 */
	class AgeSort implements Comparator<Show> {
		public int compare(Show a, Show b) {
			Age aAge = (Age) a.getShowInformation("ageRating");
			Age bAge = (Age) b.getShowInformation("ageRating");
			if (aAge.ordinal() > bAge.ordinal()) return 1;
			return -1;
		}
	}

	/**
	 * A comparator that sorts showings by genre in ascending order.
	 * 
	 * @author Team Blue
	 *
	 */
	class GenreSort implements Comparator<Show> {
		public int compare(Show a, Show b) {
			Genre aGenre = (Genre) a.getShowInformation("genre");
			Genre bGenre = (Genre) b.getShowInformation("genre");
			if (aGenre.ordinal() > bGenre.ordinal()) return 1;
			return -1;
		}
	}

	/**
	 * A comparator that sorts showings by name in ascending order.
	 * 
	 * @author Team Blue
	 *
	 */
	class NameSort implements Comparator<Show> {
		public int compare(Show a, Show b) {
			String aName = "" + a.getShowInformation("name");
			String bName = "" + b.getShowInformation("name");
			if (aName.charAt(0) < bName.charAt(0)) return 1;
			return -1;
		}
	}

	/**
	 * Sorts the list of all shows by genre.
	 * @return List<Show>
	 */
	public List<Show> sortByGenre() {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			result.add(show);
		}

		Collections.sort(result, new GenreSort());

		return result;
	}

	/**
	 * Sorts the list of all shows by name.
	 * @return List<Show>
	 */
	public List<Show> sortByName() {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			result.add(show);
		}

		Collections.sort(result, new NameSort());

		return result;
	}

	/**
	 * Sorts the list of all shows by age.
	 * @return List<Show>
	 */
	public List<Show> sortByAgeRating() {
		List<Show> result = new ArrayList<Show>();
		for (Show show : shows) {
			if (show == null) continue;
			result.add(show);
		}

		Collections.sort(result, new AgeSort());

		return result;
	}

	/**
	 * Searches for shows that contain the name parameter.
	 * @param name
	 * @return List<Show>
	 */
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

	/**
	 * Searches for a list of shows by genre.
	 * @param genre
	 * @return List<Show>
	 */
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

	/**
	 * Searches for a list of shows by age rating.
	 * @param age
	 * @return List<Show>
	 */
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

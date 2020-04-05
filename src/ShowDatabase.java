public class ShowDatabase {

	private static ShowDatabase showDatabase;
	private Show[] shows;

	private ShowDatabase() {
		shows = new Show[100];

		/* TODO: Test code. */
		for (int i = 0; i < 5; i++) {
			Show show = new Show();
			show.setShowInformation("name", "Show " + i);
			shows[i] = show;
		}
	}

	public static ShowDatabase getInstance() {
		if (showDatabase == null) {
			showDatabase = new ShowDatabase();
		}

		return showDatabase;
	}

	public Show getShowByName(String showName) {
		return null;
	}

	public void editShowInformation(Show show, String key, Object value) {
	}

	public void addShow(Show show) {
	}

	public void removeShow(Show show) {
	}

	public String toString() {
		String printout = "";
		printout += "===========================\n";
		printout += "Showings: \n";
		for (int i = 0; i < shows.length; i++) {
			printout += i + ": " + shows[i].getShowInformation("name");
		}

		return printout;
	}

	public Show[] sortByGenre() {
		return null;
	}

	public Show[] sortByName() {
		return null;
	}

	public Show[] sortByAgeRating() {
		return null;
	}

	public Show[] searchByName(String showName) {
		return null;
	}

	public Show[] searchByGenre(Enum genre) {
		return null;
	}

	public Show[] searchByAgeRating(Enum age) {
		return null;
	}
}

public class ShowDatabase {

	private static ShowDatabase showDatabase;
	private Show[] shows;

	private ShowDatabase() {
		shows = new Show[100];
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

	public void editShowInformation(Show show, ShowInformation showInformation) {
	}

	public void addShow(Show show) {
	}

	public void removeShow(Show show) {
	}

	public void listShows() {
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

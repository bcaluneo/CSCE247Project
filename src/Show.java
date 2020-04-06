import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Show {

	private static final int MAX_ATTRIBUTES = 7;

	private HashMap<String, Object> showInformation;
	private List<Review> reviews;

	public Show() {
		this.showInformation = new HashMap<String, Object>(MAX_ATTRIBUTES);

		showInformation.put("name", null); /* String */
		showInformation.put("description", null); /* String */
		showInformation.put("price", null); /* double */
		showInformation.put("ageRating", null); /* Enum (Age) */
		showInformation.put("genre", null); /* Enum (Genre) */
		showInformation.put("inTheaters", null); /* boolean */
		showInformation.put("times", null); /* String[] */

		reviews = new ArrayList<Review>();
	}

	public Object getShowInformation(String key) {
		return showInformation.get(key);
	}

	public void setShowInformation(String key, Object value) {
		if (!showInformation.containsKey(key)) {
			System.out.println("error setShowInformation(String, Object): no" +
													"information found for that key.");
			return;
		}

		showInformation.replace(key, value);
	}

	public double getAverageRating() {
		return -1;
	}

	public void addReview(Review review) {
	}

	public void deleteReview(Review review) {
	}

	public String toString() {
		String printout = "";
		printout += "****" + "\n";
		printout += "Name: " + getShowInformation("name") + "\n";
		printout += "Age Rating: " + getShowInformation("ageRating") + "\n";
		printout += "Genre: " + getShowInformation("genre") + "\n";
		printout += "Average Rating: " + getAverageRating() + "\n";
		printout += "Reviews: " + "\n";
		for (Review review : reviews) {
			printout += "\t" + review.toString();
		}
		printout += "****" + "\n";

		return printout;
	}
}

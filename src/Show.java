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
		if (reviews.size() == 0) return 0;

		int sum = 0;
		for (Review review : reviews) {
			sum += review.getRating();
		}

		return sum / reviews.size();
	}

	public Review getReviewByAuthor(String author) {
		for (Review review : reviews) {
			String username = ""+review.getAuthor().getProfileInformation("username");
			if (username.equals(author)) return review;
		}
		return null;
	}

	public Review getReviewByIndex(int index) {
		return reviews.get(index);
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public void deleteReview(Review review) {
		reviews.remove(review);
	}

	public String toString(boolean printReviews) {
		String printout = "";
		printout += "\n" + "****************" + "\n";
		printout += "Name: " + getShowInformation("name") + "\n";
		printout += "Description: " + getShowInformation("description") + "\n";
		printout += "Age Rating: " + getShowInformation("ageRating") + "\n";
		printout += "Genre: " + getShowInformation("genre") + "\n";
		printout += "Times: " + "\n";

		List<String> times = (ArrayList<String>) getShowInformation("times");
		if (times != null) {
			for (String time : times) {
				printout += time + "\n";
			}
		}

		printout += "Average Rating: " + getAverageRating() + "\n";
		if (printReviews) {
			printout += "Reviews: " + "\n";
			for (int i = 0; i < reviews.size(); i++) {
				Review review = reviews.get(i);
				printout += i + ":\n";
				printout += review.toString();
			}
		}

		return printout;
	}
}

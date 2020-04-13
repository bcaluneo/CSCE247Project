package show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A show that can be booked.
 * @author Team Blue
 *
 */
public class Show {

	/** The maximum amount of attributes for a show. */
	private static final int MAX_ATTRIBUTES = 7;

	/** Holds all the informations about the show. */
	private HashMap<String, Object> showInformation;
	
	/** A list of all reviews. */
	private List<Review> reviews;

	/**
	 * Creates an empty show instance. Fields should be set using the
	 * setShowInformation and retrieved using the getShowInformation functions.
	 */
	public Show() {
		this.showInformation = new HashMap<String, Object>(MAX_ATTRIBUTES);

		showInformation.put("name", null); /* String */
		showInformation.put("showType", null); /* Enum (ShowType) */
		showInformation.put("description", null); /* String */
		showInformation.put("price", null); /* double */
		showInformation.put("age", null); /* Enum (Age) */
		showInformation.put("genre", null); /* Enum (Genre) */
		showInformation.put("inTheaters", null); /* boolean */
		showInformation.put("times", null); /* List<String> */

		reviews = new ArrayList<Review>();
	}

	/**
	 * Returns information about the show in the form of an object.
	 * @param key
	 * @return object
	 */
	public Object getShowInformation(String key) {
		return showInformation.get(key);
	}

	/**
	 * Sets a specfic piece of show information using a key and value.
	 * @param key
	 * @param value
	 */
	public void setShowInformation(String key, Object value) {
		if (!showInformation.containsKey(key)) {
			System.out.println("error setShowInformation(String, Object): no" +
													"information found for that key.");
			return;
		}

		showInformation.replace(key, value);
	}

	/**
	 * Returns the average review rating.
	 * @return double
	 */
	public double getAverage() {
		if (reviews.size() == 0) return 0;

		int sum = 0;
		for (Review review : reviews) {
			sum += review.getRating();
		}

		return sum / reviews.size();
	}

	/**
	 * Returns a review instance from the author's username.
	 * @param author
	 * @return review
	 */
	public Review getReviewByAuthor(String author) {
		for (Review review : reviews) {
			String username = ""+review.getAuthor().getProfileInformation("username");
			if (username.equals(author)) return review;
		}
		return null;
	}

	/**
	 * Returns a review instance from an index within a list.
	 * @param index
	 * @return review
	 */
	public Review getReviewByIndex(int index) {
		return reviews.get(index);
	}

	/**
	 * Adds a review to the review list.
	 * @param review
	 */
	public void addReview(Review review) {
		reviews.add(review);
	}

	/**
	 * Removes a review from the review list.
	 * @param review
	 */
	public void deleteReview(Review review) {
		reviews.remove(review);
	}

	public String toString(boolean printReviews) {
		String printout = "";
		printout += "\n" + "****************" + "\n";
		printout += "Name: " + getShowInformation("name") + "\n";
		printout += "Type: " + getShowInformation("showType") + "\n";
		printout += "Description: " + getShowInformation("description") + "\n";
		printout += "Age Rating: " + getShowInformation("age") + "\n";
		printout += "Genre: " + getShowInformation("genre") + "\n";
		printout += "Times: " + "\n";

		List<String> times = (ArrayList<String>) getShowInformation("times");
		if (times != null) {
			for (String time : times) {
				printout += time + "\n";
			}
		}

		printout += "Average Rating: " + getAverage() + "\n";
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
	
	public List<Review> getReviews() {
		return this.reviews;
	}
}

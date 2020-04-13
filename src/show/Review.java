package show;

import user.User;

/**
 * A review class for a show.
 * 
 * @author Team Blue
 */
public class Review {
	
	private User author;
	private String description;
	private int rating;

	/**
	 * Creates a review instance.
	 * @param author
	 * @param description
	 * @param rating
	 */
	public Review(User author, String description, int rating) {
		this.author = author;
		this.description = description;
		this.rating = rating;
	}

	public User getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public int getRating() {
		return rating;
	}

	public String toString() {
		return "Author: " + this.author.getProfileInformation("username") + "\nDescription: " + this.description + "\nRating: " + this.rating + " Stars";
	}
}

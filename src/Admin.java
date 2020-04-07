/**
 * Inherit from Base Class User
 * */
public class Admin extends User {

	/**
	 * this method removes inappropriate reviews
	 */
	public void removeReview(Show show, Review review) {
		String author = ""+review.getAuthor().getProfileInformation("username");
		if (show.getReviewByAuthor(author) == null) return;

		show.deleteReview(review);
	}
}

public class Movie extends Show {

	private Review[] reviews;

	public Movie(ShowInformation showInformation) {
		setShowInformation(showInformation);
	}

	public int getAverageRating() {
		return -1;
	}

	public void deleteReview(Review review) {
	}
}

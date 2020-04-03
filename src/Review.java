public class Review {
	private User author;
	String description;
	int rating;
	
	public Review(User author, String description, int rating) {
		this.author = author;
		this.description = description;
		this.rating = rating;
	}
	
}
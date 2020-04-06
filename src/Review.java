public class Review {
	private User author;
	String description;
	int rating;
	
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
		return "Author: "+this.author.getProfileInformation("username")
			+"\nDescription: "+this.description
			+"\nRating: "+this.rating+" Stars";
	}
}
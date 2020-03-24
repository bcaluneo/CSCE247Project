import java.util.HashMap;

public class User {

	private HashMap<String, Object> profileInformation;
	private BookedShow[] bookedShows;
	private Age ageRating;

	public User() {
		this.profileInformation = new HashMap<String, Object>(13);
		this.bookedShows = new BookedShow[100];

		profileInformation.put("username", null); /* String */
		profileInformation.put("name", null); /* String */
		profileInformation.put("email", null); /* String */
		profileInformation.put("password", null); /* String */
		profileInformation.put("dob", null); /* String */
		profileInformation.put("zipCode", null); /* int */
		profileInformation.put("discount", null); /* boolean */
		profileInformation.put("isAdult", null); /* boolean */
		profileInformation.put("isStaff", null); /* boolean */
		profileInformation.put("isAdmin", null); /* boolean */
		profileInformation.put("isVIP", null); /* boolean */
		profileInformation.put("isHandicapped", null); /* boolean */
		profileInformation.put("rewardPoints", null); /* double */
	}

	public Object getProfileInformation(String key) {
		return profileInformation.get(key);
	}

	public void setProfileInformation(String key, Object value) {
		if (profileInformation.get(key) == null) {
			System.out.println("error setProfileInformation(String, Object): no" +
													"information found for that key.");
			return;
		}

		profileInformation.replace(key, value);
	}

	public void bookShow(Show show) {
	}

	public void refundShow(Show show) {
	}
}

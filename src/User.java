import java.util.HashMap;

public class User {

	private HashMap<String, Object> profileInformation;
	private BookedShow[] bookedShows;
	private Age ageRating;

	public User() {
		this.profileInformation = new HashMap<String, Object>(13);
		this.bookedShows = new BookedShow[100];

		profileInformation.put("username", null);
		profileInformation.put("name", null);
		profileInformation.put("email", null);
		profileInformation.put("password", null);
		profileInformation.put("dob", null);
		profileInformation.put("zipCode", null);
		profileInformation.put("discount", null);
		profileInformation.put("isAdult", null);
		profileInformation.put("isStaff", null);
		profileInformation.put("isAdmin", null);
		profileInformation.put("isVIP", null);
		profileInformation.put("isHandicapped", null);
		profileInformation.put("rewardPoints", null);
	}

	public Object getProfileInformation(String key) {
		return profileInformation.get(key);
	}

	public void setProfileInformation(String key, String value) {
		if (profileInformation.get(key) == null) {
			System.out.println("error: setProfileInformation(String, String): no" +
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

import java.util.HashMap;

public class User {

	private static final int MAX_SHOWS = 100;
	private static final int MAX_ATTRIBUTES = 14;

	private HashMap<String, Object> profileInformation;
	private BookedShow[] bookedShows;
	

	public User() {
		this.profileInformation = new HashMap<String, Object>(MAX_ATTRIBUTES);
		this.bookedShows = new BookedShow[MAX_SHOWS];
		
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
		profileInformation.put("isAgeRestricted",null); /* boolean */
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

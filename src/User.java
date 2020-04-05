import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class User {

	private static final int MAX_ATTRIBUTES = 13;
	private static final int MAX_PAYMENT_INFO = 4;

	private HashMap<String, Object> profileInformation, paymentInformation;
	private List<BookedShow> bookedShows;

	public User() {
		this.profileInformation = new HashMap<String, Object>(MAX_ATTRIBUTES);
		this.paymentInformation = new HashMap<String, Object>(MAX_PAYMENT_INFO);
		this.bookedShows = new ArrayList<BookedShow>();

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
		profileInformation.put("isAgeRestricted", null); /* boolean */

		paymentInformation.put("card-number", null);
		paymentInformation.put("card-date", null);
		paymentInformation.put("card-name", null);
		paymentInformation.put("card-code", null);
	}

	public Object getProfileInformation(String key) {
		return profileInformation.get(key);
	}

	public void setProfileInformation(String key, Object value) {
		/*if (profileInformation.get(key) == null) {
			System.out.println("error setProfileInformation(String, Object): no" +
													"information found for that key.");
			return;
		}*/ /* TODO: Fix this. */

		profileInformation.replace(key, value);
	}

	public Object getPaymentInformation(String key) {
		return paymentInformation.get(key);
	}

	public void setPaymentInformation(String key, Object value) {
		/*if (paymentInformation.get(key) == null) {
			System.out.println("error setProfileInformation(String, Object): no" +
													"information found for that key.");
			return;
		}*/ /* TODO: Fix this. */

		paymentInformation.replace(key, value);
	}

	public boolean isDefaultUser() {
		return getProfileInformation("name") == null;
	}

	public void bookShow(Venue venue, Theater theater, Show show, List<Seat> seats) {
		BookedShow bookedShow = new BookedShow(venue, theater, show, seats);
		bookedShows.add(bookedShow);
	}

	public void refundShow(Show show) {
	}
}

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

		profileInformation.put("username", "Anonymous"); /* String */
		profileInformation.put("name", ""); /* String */
		profileInformation.put("email", ""); /* String */
		profileInformation.put("password", ""); /* String */
		profileInformation.put("dob", null); /* int[3] {month, day, year} */
		profileInformation.put("zipCode", -1); /* int */
		profileInformation.put("discount", false); /* boolean */
		profileInformation.put("isAdult", false); /* boolean */
		profileInformation.put("isStaff", false); /* boolean */
		profileInformation.put("isAdmin", false); /* boolean */
		profileInformation.put("isVIP", false); /* boolean */
		profileInformation.put("isHandicapped", false); /* boolean */
		profileInformation.put("rewardPoints", -1); /* double */
		profileInformation.put("isAgeRestricted", false); /* boolean */

		paymentInformation.put("card-number", null);
		paymentInformation.put("card-date", null);
		paymentInformation.put("card-name", null);
		paymentInformation.put("card-code", null);
	}

	public Object getProfileInformation(String key) {
		return profileInformation.get(key);
	}

	public void setProfileInformation(String key, Object value) {
		if (!profileInformation.containsKey(key)) {
			System.out.println("error setProfileInformation(String, Object): no" +
													"information found for that key.");
			return;
		}

		profileInformation.replace(key, value);
	}

	public Object getPaymentInformation(String key) {
		return paymentInformation.get(key);
	}

	public void setPaymentInformation(String key, Object value) {
		if (!paymentInformation.containsKey(key)) {
			System.out.println("error setProfileInformation(String, Object): no" +
													"information found for that key.");
			return;
		}

		paymentInformation.replace(key, value);
	}

	public boolean isDefaultUser() {
		return getProfileInformation("username").equals("Anonymous"); /* Put this into a variable. */
	}

	public boolean isStaff() {
		return (boolean) getProfileInformation("isStaff") == true;
	}

	public boolean isAdmin() {
		return (boolean) getProfileInformation("isAdmin") == true;
	}

	public void bookShow(Venue venue, Theater theater, Show show, List<Seat> seats) {
		BookedShow bookedShow = new BookedShow(venue, theater, show, seats);
		bookedShows.add(bookedShow);
		bookedShow.printTicket();
	}

	public void refundShow(Show show) {
	}

	public List<BookedShow> getBookedShows() {
		return bookedShows;
	}
}

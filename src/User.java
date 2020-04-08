import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * A basic user class.
 * @author Team Blue
 *
 */
public class User {

	/** The maximum number of attributes the user can have. */
	private static final int MAX_ATTRIBUTES = 13;
	
	/** The maximum number of payment information. */
	private static final int MAX_PAYMENT_INFO = 4;

	/** Stores the payment and profile information. */
	private HashMap<String, Object> profileInformation, paymentInformation;
	
	/** A list of booked shows. */
	private List<BookedShow> bookedShows;
	
	/** A list of children (if parent account). */
	private List<User> children;

	/**
	 * Creates a new empty user instance. The profile and payment information should
	 * be modified using the setProfileInformation and setPaymentInformation and retrieval
	 * should be through getProfileInformation and getPaymentInformation.
	 */
	public User() {
		this.profileInformation = new HashMap<String, Object>(MAX_ATTRIBUTES);
		this.paymentInformation = new HashMap<String, Object>(MAX_PAYMENT_INFO);
		this.bookedShows = new ArrayList<BookedShow>();
		this.children = new ArrayList<User>();

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

	/**
	 * Returns information about the user's profile in the form of an object.
	 * @param key
	 * @return object
	 */
	public Object getProfileInformation(String key) {
		return profileInformation.get(key);
	}

	/**
	 * Sets a specfic piece of the user's profile using a key and value.
	 * @param key
	 * @param value
	 */
	public void setProfileInformation(String key, Object value) {
		if (!profileInformation.containsKey(key)) {
			System.out.println("error setProfileInformation(String, Object): no" +
													"information found for that key.");
			return;
		}

		profileInformation.replace(key, value);
	}

	/**
	 * Returns information about the user's payment information in the form of an object.
	 * @param key
	 * @return object
	 */
	public Object getPaymentInformation(String key) {
		return paymentInformation.get(key);
	}

	/**
	 * Sets a specfic piece of the user's payment information using a key and value.
	 * @param key
	 * @param value
	 */
	public void setPaymentInformation(String key, Object value) {
		if (!paymentInformation.containsKey(key)) {
			System.out.println("error setProfileInformation(String, Object): no" +
													"information found for that key.");
			return;
		}

		paymentInformation.replace(key, value);
	}

	/**
	 * Determines whether or not the current user is a guest user.
	 * @return boolean
	 */
	public boolean isDefaultUser() {
		return getProfileInformation("username").equals("Anonymous"); /* Put this into a variable. */
	}

	/**
	 * Determines whether or not the user is a staff member.
	 * @return boolean
	 */
	public boolean isStaff() {
		return (boolean) getProfileInformation("isStaff") == true;
	}

	/**
	 * Determines whether or not the user is an admin.
	 * @return boolean
	 */
	public boolean isAdmin() {
		return (boolean) getProfileInformation("isAdmin") == true;
	}

	/**
	 * Adds a show to the list of the user's booked shows.
	 * @param venue
	 * @param theater
	 * @param show
	 * @param seats
	 */
	public void bookShow(Venue venue, Theater theater, Show show, List<Seat> seats) {
		BookedShow bookedShow = new BookedShow(venue, theater, show, seats);
		bookedShows.add(bookedShow);
		bookedShow.printTicket();
	}

	/**
	 * Refunds the user the amount spent on a show.
	 * @param show
	 */
	public void refundShow(Show show) {
	}

	public List<BookedShow> getBookedShows() {
		return bookedShows;
	}

	public List<User> getChildren() {
		return children;
	}

	/**
	 * Adds a child if the user is a parent account.
	 * @param child
	 */
	public void addChild(User child) {
		this.children.add(child);
	}

	/**
	 * Removes a child from the parent's account.
	 * @param child
	 */
	public void removeChild(User child) {
		this.children.remove(child);
	}
}

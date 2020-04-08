import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

	public static void writeUsers() {
		UserManager user = UserManager.getInstance();
		List<User> users = user.getUsers();

		try {
			PrintWriter pw = new PrintWriter(new File(USER_DATA));
			pw.println("[");
			for (int i = 0; i < users.size(); i++) {
				JSONObject obj = getUserJSON(users.get(i));
				pw.println(obj.toString());
			}
			pw.println("]");
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeShows() {
		ShowDatabase showdb = ShowDatabase.getInstance();
		List<Show> shows = showdb.getShows();

		try {
			PrintWriter pw = new PrintWriter(new File(SHOW_DATA));
			pw.println("[");
			for (int i = 0; i < shows.size(); i++) {
				JSONObject obj = getShowJSON(shows.get(i));
				pw.println(obj.toString());
			}
			pw.println("]");
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeVenues() {
		VenueDatabase venuedb = VenueDatabase.getInstance();
		List<Venue> venues = venuedb.getVenues();

		try {
			PrintWriter pw = new PrintWriter(new File(SHOW_DATA));
			pw.println("[");
			for (int i = 0; i < venues.size(); i++) {
				JSONObject obj = getVenueJSON(venues.get(i));
				pw.println(obj.toString());
			}
			pw.println("]");
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JSONObject getUserJSON(User user) {
		JSONObject profileInformation = new JSONObject();

		profileInformation.put(USERNAME, user.getProfileInformation("username"));
		profileInformation.put(NAME, user.getProfileInformation("name"));
		profileInformation.put(EMAIL, user.getProfileInformation("email"));
		profileInformation.put(PASSWORD, user.getProfileInformation("password"));

		int[] dob = (int[]) user.getProfileInformation("dob");
		if (dob == null) {
			profileInformation.put(DOB, "-1,-1,-1");
		} else {
			profileInformation.put(DOB, dob[0]+","+dob[1]+","+dob[2]);
		}

		profileInformation.put(ZIPCODE, user.getProfileInformation("zipCode"));
		profileInformation.put(DISCOUNT, user.getProfileInformation("discount"));
		profileInformation.put(IS_ADULT, user.getProfileInformation("isAdult"));
		profileInformation.put(IS_STAFF, user.getProfileInformation("isStaff"));
		profileInformation.put(IS_ADMIN, user.getProfileInformation("isAdmin"));
		profileInformation.put(IS_VIP, user.getProfileInformation("isVip"));
		profileInformation.put(IS_HANDICAPPED, user.getProfileInformation("isHandicapped"));
		profileInformation.put(REWARDPOINTS, user.getProfileInformation("rewardPoints"));
		profileInformation.put(AGE_RESTRICTED, user.getProfileInformation("isAgeRestricted"));

		return profileInformation;
	}
	
	public static JSONObject getShowJSON(Show show) {
		JSONObject result = new JSONObject();
		
		result.put(SHOW_NAME, show.getShowInformation("name"));
		result.put(SHOW_DESCRIPTION, show.getShowInformation("description"));
		result.put(SHOW_PRICE, show.getShowInformation("price"));
		result.put(SHOW_RATING, show.getShowInformation("ageRating"));
		result.put(SHOW_GENRE, show.getShowInformation("genre"));
		result.put(SHOW_INTHEATERS, show.getShowInformation("inTheaters"));
		
		List<String> times = (ArrayList<String>) show.getShowInformation("times");
		String timesString = "";
		for (String string : times) {
			timesString += string + ",";
		}
		
		result.put(SHOW_NAME, timesString);

		return result;
	}
	
	public static JSONObject getVenueJSON(Venue venue) {
		JSONObject result = new JSONObject();
		
		result.put(VENUE_NAME, venue.getName());
		result.put(VENUE_LOCATION, venue.getLocation());
		
		return result;
	}
}
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONTokener;
import org.json.JSONWriter;
import org.json.CDL;



public class DataLoader extends DataConstants {

	public static ArrayList<User> loadUser() {
		List<User> users = new ArrayList<User>();

		try {
			FileReader reader = new FileReader(USER_DATA);
			CDL parser = new CDL();
			JSONArray usersJSON = CDL.rowToJSONArray(new JSONTokener(reader));


			for(int i=0; i < usersJSON.size(); i++) {
				JSONObject userJSON = (JSONObject)usersJSON.get(i);

				String username = (String)userJSON.get(USERNAME);
				String name = (String)userJSON.get(NAME);
				String email = (String)userJSON.get(EMAIL);
				String password = (String)userJSON.get(PASSWORD);
				int dob = (int)userJSON.get(DOB);
				int zipCode = (int)userJSON.get(zipCode);
				int discount = (int)userJSON.get(DISCOUNT);
				boolean isAdult = (boolean)userJSON.get(IS_ADULT);
				boolean isStaff = (boolean)userJSON.get(IS_STAFF);
				boolean isAdmin = (boolean)userJSON.get(IS_ADMIN);
				boolean isVip = (boolean)userJSON.get(IS_VIP);
				boolean isHandicapped = (boolean)userJSON.get(IS_HANDICAPPED);
				int rewardPoints = (int)userJSON.get(REWARDPOINTS);
				boolean isAgeRestricted = (boolean)userJSON.get(AGE_RESTRICTED);

				User user = new User();
				user.setProfileInformation("username", username);
				user.setProfileInformation("name", name);
				user.setProfileInformation("email", email);
				user.setProfileInformation("password", password);
				user.setProfileInformation("dob", dob);
				user.setProfileInformation("zipCode", zipCode);
				user.setProfileInformation("discount", discount);
				user.setProfileInformation("isAdult", isAdult);
				user.setProfileInformation("isStaff", isStaff);
				user.setProfileInformation("isAdmin", isAdmin);
				user.setProfileInformation("isVIP", isVIP);
				user.setProfileInformation("isHandicapped", isHandicapped);
				user.setProfileInformation("isAgeRestricted", isAgeRestricted);
			}

			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}

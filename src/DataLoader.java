import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {

	public static List<User> loadUsers() {
		List<User> users = new ArrayList<User>();

		try {
			FileReader reader = new FileReader(USER_DATA);
			JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);

			for (int i = 0; i < usersJSON.size(); i++) {
				JSONObject userJSON = (JSONObject) usersJSON.get(i);

				String username = (String) userJSON.get(USERNAME);
				String name = (String) userJSON.get(NAME);
				String email = (String) userJSON.get(EMAIL);
				String password = (String) userJSON.get(PASSWORD);
				String[] dobLine = ("" + userJSON.get(DOB)).split(",");
				int[] dob = new int[3];
				for (int j = 0; j < 3; j++) {
					dob[j] = Integer.parseInt(dobLine[j]);
				}

				int zipCode = Integer.parseInt("" + userJSON.get(ZIPCODE));
				boolean discount = Boolean.parseBoolean(""+userJSON.get(DISCOUNT));
				boolean isAdult = Boolean.parseBoolean(""+userJSON.get(IS_ADULT));
				boolean isStaff = Boolean.parseBoolean(""+userJSON.get(IS_STAFF));
				boolean isAdmin = Boolean.parseBoolean(""+userJSON.get(IS_ADMIN));
				boolean isVIP = Boolean.parseBoolean(""+userJSON.get(IS_VIP));
				boolean isHandicapped = Boolean.parseBoolean(""+userJSON.get(IS_HANDICAPPED));
				double rewardPoints = Double.parseDouble(""+userJSON.get(REWARDPOINTS));
				boolean isAgeRestricted = Boolean.parseBoolean(""+userJSON.get(AGE_RESTRICTED));

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
				user.setProfileInformation("rewardPoints", rewardPoints);
				user.setProfileInformation("isAgeRestricted", isAgeRestricted);
				
				users.add(user);
			}

			return users;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
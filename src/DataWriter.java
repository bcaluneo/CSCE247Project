import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

	public static void writeUsers() {
		UserManager user = UserManager.getInstance();
		List<User> users = user.getUsers();
		JSONArray jsonUsers = new JSONArray();

		for (int i = 0; i < users.size(); i++) {
			jsonUsers.add(getUserJSON(users.get(i)));
		}

		try {
			PrintWriter pw = new PrintWriter(new File(USER_DATA));
			System.out.println(jsonUsers.toString());
			pw.append("\n"+jsonUsers.toString()+"\n");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static JSONObject getUserJSON(User user) {
		JSONObject profileInformation = new JSONObject();

		profileInformation.put(AGE_RESTRICTED, user.getProfileInformation("isAgeRestricted"));
		profileInformation.put(REWARDPOINTS, user.getProfileInformation("rewardPoints"));
		profileInformation.put(IS_HANDICAPPED, user.getProfileInformation("isHandicapped"));
		profileInformation.put(IS_VIP, user.getProfileInformation("isVip"));
		profileInformation.put(IS_ADMIN, user.getProfileInformation("isAdmin"));
		profileInformation.put(IS_STAFF, user.getProfileInformation("isStaff"));
		profileInformation.put(IS_ADULT, user.getProfileInformation("isAdult"));
		profileInformation.put(DISCOUNT, user.getProfileInformation("discount"));
		profileInformation.put(ZIPCODE, user.getProfileInformation("zipCode"));
		profileInformation.put(DOB, user.getProfileInformation("dob"));
		profileInformation.put(PASSWORD, user.getProfileInformation("password"));
		profileInformation.put(EMAIL, user.getProfileInformation("email"));
		profileInformation.put(NAME, user.getProfileInformation("name"));
		profileInformation.put(USERNAME, user.getProfileInformation("username"));

		return profileInformation;
	}
}
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
}
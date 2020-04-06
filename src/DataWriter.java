
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
	
	
	public static void writeUser() {
		UserManager user = UserManager.getInstance();
		ArrayList<User> users = UserManager.getCurrentUser();
		JSONArray jsonUsers = new JSONArray();
		
		for(int i=0; i< users.size(); i++) {
			jsonUsers.add(getUserJSON(users.get(i)));
		}
		
        try (FileWriter file = new FileWriter(USER_DATA)) {
 
            file.write(jsonUsers.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject getUserJSON(UserManager user) {
		JSONObject profileInformation = new JSONObject();
	
		profileInformation.put(USERNAME, user.getProfileInformation(username));
		profileInformation.put(NAME, user.getProfileInformation(name));
		profileInformation.put(EMAIL, user.getProfileInformation(email));
		profileInformation.put(PASSWORD,user.getProfileInformation(password));
		profileInformation.put(DOB,user.getProfileInformation(dob));
		profileInformation.put(ZIPCODE,user.getProfileInformation(zipCode));
		profileInformation.put(DISCOUNT,user.getProfileInformation(discount));
		profileInformation.put(IS_ADULT,user.getProfileInformation(isAdult));
		profileInformation.put(IS_STAFF,user.getProfileInformation(isStaff));
		profileInformation.put(IS_ADMIN,user.getProfileInformation(isAdmin));
		profileInformation.put(IS_VIP,user.getProfileInformationisVip());
		profileInformation.put(IS_HANDICAPPED,user.getProfileInformation(isHandicapped));
		profileInformation.put(REWARDPOINTS,user.getProfileInformation(rewardPoints));
		profileInformation.put(AGE_RESTRICTED,user.getProfileInformation(isAgeRestricted));
		
        
        return profileInformation;
	}
}
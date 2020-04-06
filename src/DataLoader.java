import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader {
	
	public static ArrayList<User> loadUser() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			FileReader reader = new FileReader(USER_DATA);
			JSONParser parser = new JSONParser();
			JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
			
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
				
				
				users.add(new User(username, name, email, password, dob, zipCode, 
						discount, isAdult, isStaff, isAdmin, isVip, isHandicapped,
						isAgeRestricted));
			}
			
			return users;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

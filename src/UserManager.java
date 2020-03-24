public class UserManager {
	
	private static UserManager userManager;
	
	public static UserManager getInstance() {
		if (userManager == null) {
			userManager = new UserManager();
		}
		
		return userManager;
	}
	
	private UserManager() {
	}
}
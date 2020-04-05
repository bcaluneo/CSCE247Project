public class UserManager {

	private static UserManager userManager;
	private User[] users;
	private boolean loggedIn;
	private User currentUser;

	private UserManager() {
		users = new User[100];
		currentUser = new User();
	}

	public static UserManager getInstance() {
		if (userManager == null) {
			userManager = new UserManager();
		}

		return userManager;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void createUser(User user) {
	}

	public void deleteUser(User user) {
	}

	public void loginUser(User user) {
	}

	public void logoutUser(User user) {
	}

	public User getUserByName(String username) {
		return null;
	}
}

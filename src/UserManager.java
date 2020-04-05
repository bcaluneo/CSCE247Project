import java.util.Scanner;
import java.util.ArrayList;

public class UserManager {

	private static UserManager userManager;
	private ArrayList<User> users;
	private boolean loggedIn;
	private User currentUser;
	Scanner scanner = new Scanner(System.in);
	
	private UserManager() {
		users = new ArrayList<User>();
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

	public void createUser() {
		User user = new User();
		boolean Continue = true;
		String string;
		while(Continue){
			System.out.println("Enter your email");
			string = scanner.next();
			if(emailExists(string)) {
				System.out.println("This email is already in use.");
			} else {
				user.setProfileInformation("email", string);
				Continue = false;
			}
		}
		Continue = true;
		while(Continue){
			System.out.println("Enter your username");
			string = scanner.next();
			if(usernameExists(string)) {
				System.out.println("This username is already in use.");
			} else {
				user.setProfileInformation("username", string);
				Continue = false;
			}
		}
		System.out.println("Create a password");
		string = scanner.next();
		user.setProfileInformation("password", string);
		users.add(user);
	}
	
	public boolean emailExists(String email) {
		for(User user : users) {
			if(user.getProfileInformation("email")==email) {
				return true;
			}
		}
		return false;
	}
	
	public boolean usernameExists(String username) {
		for(User user : users) {
			if(user.getProfileInformation("username")==username) {
				return true;
			}
		}
		return false;
	}

	public void deleteUser(String email) {
		if(emailExists(email)){
			int account = 0;
			for(User user : users) {
				if(user.getProfileInformation("email")==email) {
					users.remove(account);
				}
				account++;
			}
		}
	}

	public void loginUser() {
		User user = new User();
		boolean Continue = true;
		String string;
		while(Continue){
			System.out.println("Enter your email");
			string = scanner.next();
			if(emailExists(string)) {
				user = getUserByEmail(string);
				Continue = false;
			} else {
				System.out.println("Email is invalid");
			}
		}
		Continue=true;
		while(Continue){
			System.out.println("Enter your password");
			string = scanner.next();
			if(string==user.getProfileInformation("password")) {
				this.currentUser=user;
				Continue = false;
			} else {
				System.out.println("Password is invalid");
			}
		}
	}

	public void logoutUser() {
		this.currentUser=null;
	}

	public User getUserByEmail(String email) {
		for(User user : users) {
			if(user.getProfileInformation("email")==email) {
				return user;
			}
		}
		return null;
	}
	
}



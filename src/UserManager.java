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

		int month=0,day=0,year=0;
		Continue = true;
		while(Continue) {
			System.out.println("Enter your date of birth month");
			month = scanner.nextInt();
			if(month>12 || month<1) {
				System.out.println("Number entered is invalid");
			} else {
				Continue=false;
			}
		}
		Continue = true;
		while(Continue) {
			System.out.println("Enter your date of birth day");
			day = scanner.nextInt();
			if(day<1||day>31) {
				System.out.println("Number entered is invalid");
			} else {
				Continue=false;
			}
		}
		Continue = true;
		while(Continue) {
			System.out.println("Enter your date of birth year");
			year = scanner.nextInt();
			if(year>2020 || year<1900) {
				System.out.println("Number entered is invalid");
			} else {
				Continue=false;
			}
		}
		int[] dob = {month,day,year};
		user.setProfileInformation("dob", dob);

		System.out.println("Create a password");
		string = scanner.next();
		user.setProfileInformation("password", string);
		users.add(user);
		currentUser = user;
		System.out.println("Loggin in as "+user.getProfileInformation("username"));
	}

	public boolean emailExists(String email) {
		for(User user : users) {
			if(user.getProfileInformation("email").equals(email)) {
				return true;
			}
		}
		return false;
	}

	public boolean usernameExists(String username) {
		for(User user : users) {
			if(user.getProfileInformation("username").equals(username)) {
				return true;
			}
		}
		return false;
	}

	public void deleteUser(String email) {
		if(emailExists(email)){
			int account = 0;
			for(User user : users) {
				if(user.getProfileInformation("email").equals(email)) {
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
			if(user.getProfileInformation("password").equals(string)) {
				this.currentUser=user;
				System.out.println("Loggin in as "+user.getProfileInformation("username"));
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
			if(user.getProfileInformation("email").equals(email)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getUsers() {
		return this.users;
	}

}

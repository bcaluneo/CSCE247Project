import java.util.List;
import java.util.Scanner;

/**
 * Maintains a list of all the program users.
 * 
 * @author Team Blue
 *
 */
public class UserManager {

	private static UserManager userManager;
	Scanner scanner = new Scanner(System.in);

	/** A list of all users. */
	private List<User> users;

	/** The current user. */
	private User currentUser;

	/**
	 * Initializes the user manager.
	 */
	private UserManager() {
		users = DataLoader.loadUsers();
		currentUser = new User();
	}

	public static UserManager getInstance() {
		if (userManager == null) {
			userManager = new UserManager();
		}

		return userManager;
	}

	/**
	 * Returns the current logged in user.
	 * 
	 * @return
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Creates a new user account.
	 * 
	 * @return User
	 */
	public User createUser() {
		User user = new User();
		boolean Continue = true;
		String string;
		while (Continue) {
			System.out.println("Enter your email");
			string = scanner.next();
			if (emailExists(string)) {
				System.out.println("This email is already in use.");
			} else {
				user.setProfileInformation("email", string);
				Continue = false;
			}
		}
		Continue = true;
		while (Continue) {
			System.out.println("Enter your username");
			string = scanner.next();
			if (usernameExists(string)) {
				System.out.println("This username is already in use.");
			} else {
				user.setProfileInformation("username", string);
				Continue = false;
			}
		}

		System.out.println("Create a password");
		string = scanner.next();
		user.setProfileInformation("password", string);
		return user;
	}

	/**
	 * Extends the createUser function to create an adult user account.
	 */
	public void createAdultAccount() {
		int[] dob = makeDob();
		while (dob[2] > 2008) {
			System.out.println("Age entered is too young.");
			dob = makeDob();
		}
		User user = createUser();
		user.setProfileInformation("dob", dob);
		user.setProfileInformation("isAdult", true);
		currentUser = user;
		users.add(user);
		System.out.println("Logging in as " + user.getProfileInformation("username"));
	}

	/**
	 * Creates a child account under a parent account.
	 * 
	 * @param parent
	 */
	public void createChildAccount(User parent) {
		if (parent instanceof Child) {
			System.out.println("You cannot use a child account as the parent.");
		} else {
			Child child = new Child();
			child.setParent(parent);
			int[] dob = makeDob();
			while (dob[2] < 2008) {
				System.out.println("Age entered is too old.");
				dob = makeDob();
			}
			User user = createUser();
			child.setProfileInformation("username", user.getProfileInformation("username"));
			child.setProfileInformation("email", user.getProfileInformation("email"));
			child.setProfileInformation("password", user.getProfileInformation("password"));
			child.setProfileInformation("dob", dob);
			child.setProfileInformation("discount", true);
			currentUser = child;
			users.add(child);

			System.out.println("Logging in as " + child.getProfileInformation("username"));
		}
	}

	/**
	 * Toggles a user's account access to staff functions.
	 * @param user
	 */
	public void toggleStaffAccount(User user) {
		if (emailExists(String.valueOf(user.getProfileInformation("email")))) {
			user.setProfileInformation("isStaff", !(Boolean) user.getProfileInformation("isStaff"));
		} else {
			System.out.println("Account doesn't exist");
		}
	}

	/**
	 * Toggles a user's account access to staff and admin functions.
	 * @param user
	 */
	public void toggleAdminAccount(User user) {
		if (emailExists(String.valueOf(user.getProfileInformation("email")))) {
			user.setProfileInformation("isAdmin", !(Boolean) user.getProfileInformation("isAdmin"));
		} else {
			System.out.println("Account doesn't exist");
		}
	}

	/**
	 * Determines whether the given email address is registered to a user.
	 * @param email
	 * @return boolean
	 */
	public boolean emailExists(String email) {
		for (User user : users) {
			if (user.getProfileInformation("email").equals(email)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines whether the given username is registered to a user.
	 * @param username
	 * @return boolean
	 */
	public boolean usernameExists(String username) {
		for (User user : users) {
			if (user.getProfileInformation("username").equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a user instance if the email is found within the list.
	 * @param email
	 * @return user
	 */
	public User getUserByEmail(String email) {
		for (User user : users) {
			if (user.getProfileInformation("email").equals(email)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Returns a user using an index from the list.
	 * @param index
	 * @return user
	 */
	public User getUserByIndex(int index) {
		return users.get(index);
	}

	/**
	 * Creates the date of birth array used for user accounts.
	 * @return int[3]
	 */
	public int[] makeDob() {
		int month = 0, day = 0, year = 0;
		boolean Continue = true;
		while (Continue) {
			try {
				System.out.println("Enter your date of birth month");
				month = scanner.nextInt();
				if (month > 12 || month < 1) {
					System.out.println("Number entered is invalid");
				} else {
					Continue = false;
				}
			} catch (Exception exception) {
				System.out.println("Invalid input.");
				scanner.next();
			}
		}
		Continue = true;
		while (Continue) {
			try {
				System.out.println("Enter your date of birth day");
				day = scanner.nextInt();
				if (day < 1 || day > 31) {
					System.out.println("Number entered is invalid");
				} else {
					Continue = false;
				}
			} catch (Exception exception) {
				System.out.println("Invalid input.");
				scanner.next();
			}
		}
		Continue = true;
		while (Continue) {
			try {
				System.out.println("Enter your date of birth year");
				year = scanner.nextInt();
				if (year > 2020 || year < 1900) {
					System.out.println("Number entered is invalid");
				} else {
					Continue = false;
				}
			} catch (Exception exception) {
				System.out.println("Invalid input.");
				scanner.next();
			}
		}
		int[] dob = { month, day, year };
		return dob;
	}

	/**
	 * Deletes a user from the list using the given email.
	 * @param email
	 */
	public void deleteUser(String email) {
		if (emailExists(email)) {
			int account = 0;
			for (User user : users) {
				if (user.getProfileInformation("email").equals(email)) {
					users.remove(account);
				}
				account++;
			}
		}
	}

	/**
	 * Logs the user into another account.
	 */
	public void login() {
		this.currentUser = loginUser();
		if (this.currentUser != null) {
			System.out.println("Logging in as " + currentUser.getProfileInformation("username"));
		}
	}

	/**
	 * Gets an account for the user to login to.
	 * @return user
	 */
	public User loginUser() {
		User user = new User();
		boolean Continue = true;
		String string;
		while (Continue) {
			System.out.println("Enter your email");
			string = scanner.next();
			if (emailExists(string)) {
				user = getUserByEmail(string);
				Continue = false;
			} else {
				System.out.println("Email is invalid, go back to menu? Enter \"yes\" or \"no\"");
				string = scanner.next();
				if (string.equals("yes")) {
					return null;
				}
			}
		}
		Continue = true;
		while (Continue) {
			System.out.println("Enter your password");
			string = scanner.next();
			if (user.getProfileInformation("password").equals(string)) {
				Continue = false;
			} else {
				System.out.println("Password is invalid, go back to menu? Enter \"yes\" or \"no\"");
				string = scanner.next();
				if (string.equals("yes")) {
					return null;
				}
			}
		}
		return user;
	}

	/**
	 * Logs the user out the current account.
	 */
	public void logoutUser() {
		System.out.println("Logging out");
		this.currentUser = new User();
	}

	public String toString() {
		String printout = "";
		printout += "===========================\n";
		printout += "User Accounts: \n";
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i) != null) printout += i + ": " + users.get(i).getProfileInformation("username") + "\n";
		}

		return printout;
	}

	public List<User> getUsers() {
		return this.users;
	}
}

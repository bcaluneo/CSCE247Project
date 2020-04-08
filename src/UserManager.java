import java.util.List;
import java.util.Scanner;

public class UserManager {

	private static UserManager userManager;
	private List<User> users;
	private User currentUser;
	Scanner scanner = new Scanner(System.in);

	private UserManager() {
		users = DataLoader.loadUsers();
		currentUser = new User();

		User adminAccount = new User();
		adminAccount.setProfileInformation("username", "Master Control");
		adminAccount.setProfileInformation("email", "def@email.com");
		adminAccount.setProfileInformation("password", "1234");
		adminAccount.setProfileInformation("isStaff", true);
		adminAccount.setProfileInformation("isAdmin", true);

		users.add(currentUser);
		users.add(adminAccount);
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

	public User createUser() {
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
		return user;
	}

	public void createAdultAccount() {
		int[] dob = makeDob();
		while(dob[2]>2008) {
			System.out.println("Age entered is too young.");
			dob = makeDob();
		}
		User user = createUser();
		user.setProfileInformation("dob", dob);
		user.setProfileInformation("isAdult", true);
		currentUser = user;
		users.add(user);
		System.out.println("Logging in as "+user.getProfileInformation("username"));
	}

	public void createChildAccount(User parent) {
		if(parent instanceof Child) {
			System.out.println("You cannot use a child account as the parent.");
		} else {
			Child child = new Child();
			child.setParent(parent);
			int[] dob = makeDob();
			while(dob[2]<2008) {
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

			System.out.println("Logging in as "+child.getProfileInformation("username"));
		}
	}

	public void setAsStaff(User user) {
		if(emailExists(String.valueOf(user.getProfileInformation("email")))) {
			user.setProfileInformation("isStaff", true);
		} else {
			System.out.println("Account doesn't exist");
		}
	}

	public void setAsAdmin(User user) {
		if(emailExists(String.valueOf(user.getProfileInformation("email")))) {
			user.setProfileInformation("isAdmin", true);
		} else {
			System.out.println("Account doesn't exist");
		}
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

	public int[] makeDob() {
		int month=0,day=0,year=0;
		boolean Continue = true;
		while(Continue) {
			try {
				System.out.println("Enter your date of birth month");
				month = scanner.nextInt();
				if(month>12 || month<1) {
					System.out.println("Number entered is invalid");
				} else {
					Continue=false;
				}
			} catch(Exception exception) {
				System.out.println("Invalid input.");
				scanner.next();
			}
		}
		Continue = true;
		while(Continue) {
			try {
				System.out.println("Enter your date of birth day");
				day = scanner.nextInt();
				if(day<1||day>31) {
					System.out.println("Number entered is invalid");
				} else {
					Continue=false;
				}
			} catch(Exception exception) {
				System.out.println("Invalid input.");
				scanner.next();
			}
		}
		Continue = true;
		while(Continue) {
			try {
				System.out.println("Enter your date of birth year");
				year = scanner.nextInt();
				if(year>2020 || year<1900) {
					System.out.println("Number entered is invalid");
				} else {
					Continue=false;
				}
			} catch(Exception exception) {
				System.out.println("Invalid input.");
				scanner.next();
			}
		}
		int[] dob = {month,day,year};
		return dob;
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

	public void login() {
		this.currentUser=loginUser();
		if(this.currentUser!=null) {
			System.out.println("Logging in as "+currentUser.getProfileInformation("username"));
		}
	}


	public User loginUser() {
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
				System.out.println("Email is invalid, go back to menu? Enter \"yes\" or \"no\"");
				string = scanner.next();
				if(string.equals("yes")) {
					return null;
				}
			}
		}
		Continue=true;
		while(Continue){
			System.out.println("Enter your password");
			string = scanner.next();
			if(user.getProfileInformation("password").equals(string)) {
				Continue = false;
			} else {
				System.out.println("Password is invalid, go back to menu? Enter \"yes\" or \"no\"");
				string = scanner.next();
				if(string.equals("yes")) {
					return null;
				}
			}
		}
		return user;
	}


	public void logoutUser() {
		System.out.println("Logging out");
		this.currentUser = new User();
	}

	public User getUserByEmail(String email) {
		for(User user : users) {
			if(user.getProfileInformation("email").equals(email)) {
				return user;
			}
		}
		return null;
	}

	public User getUserByIndex(int index) {
		return users.get(index);
	}

	public String toString() {
		String printout = "";
    printout += "===========================\n";
    printout += "User Accounts: \n";
    for (int i = 0; i < users.size(); i++) {
    	if(users.get(i)!=null)
    		printout += i + ": " + users.get(i).getProfileInformation("username") +"\n";
		}

    return printout;
	}

	public List<User> getUsers() {
		return this.users;
	}

}

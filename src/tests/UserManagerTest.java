package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import managers.UserManager;
import user.User;

class UserManagerTest {

	@Test
	void testCurrentUser() {
		UserManager.getInstance().logoutUser();
		assertEquals(true, UserManager.getInstance().getCurrentUser().isDefaultUser());
	}

	@Test
	void testCreateUser() {
		UserManager.getInstance().getUsers().clear();
		User user = UserManager.getInstance().createUser();

		String userEmail = "" + user.getProfileInformation("email");
		String userUsername = "" + user.getProfileInformation("username");
		String userPassword = "" + user.getProfileInformation("password");

		assertEquals("t@t.com", userEmail);
		assertEquals("t", userUsername);
		assertEquals("1", userPassword);
	}

	@Test
	void testCreateDob() {
		int[] dob = UserManager.getInstance().makeDob();

		assertEquals(1, dob[0]);
		assertEquals(15, dob[1]);
		assertEquals(2000, dob[2]);
	}

	@Test
	void testCreateAdultAccount() {
		UserManager um = UserManager.getInstance();
		um.getUsers().clear();
		um.createAdultAccount();

		assertEquals(true, Boolean.parseBoolean("" + um.getUsers().get(0).getProfileInformation("isAdult")));
	}

	@Test
	void testCreateChildAccount() {
		UserManager um = UserManager.getInstance();
		um.getUsers().clear();
		um.createChildAccount(null);

		assertEquals(false, Boolean.parseBoolean("" + um.getUsers().get(0).getProfileInformation("isAdult")));
	}

	@Test
	void testToggleStaffAccess() {
		User user = new User();
		user.setProfileInformation("email", "t@t.com");
		user.setProfileInformation("username", "t");
		user.setProfileInformation("password", "1");

		UserManager.getInstance().getUsers().clear();
		UserManager.getInstance().getUsers().add(user);

		UserManager.getInstance().toggleStaffAccount(user);

		assertEquals(true, Boolean.parseBoolean("" + user.getProfileInformation("isStaff")));
		assertEquals(false, Boolean.parseBoolean("" + user.getProfileInformation("isAdmin")));

		UserManager.getInstance().toggleAdminAccount(user);

		assertEquals(true, Boolean.parseBoolean("" + user.getProfileInformation("isStaff")));
		assertEquals(true, Boolean.parseBoolean("" + user.getProfileInformation("isAdmin")));
	}

	@Test
	void testGetUser() {
		User user = new User();
		user.setProfileInformation("email", "t@t.com");
		user.setProfileInformation("username", "t");
		user.setProfileInformation("password", "1");

		UserManager.getInstance().getUsers().clear();
		UserManager.getInstance().getUsers().add(user);

		assertEquals(user, UserManager.getInstance().getUserByEmail("t@t.com"));
		assertEquals(user, UserManager.getInstance().getUserByIndex(0));
	}

	@Test
	void testDeleteUser() {
		User user = new User();
		user.setProfileInformation("email", "t@t.com");
		user.setProfileInformation("username", "t");
		user.setProfileInformation("password", "1");

		UserManager.getInstance().getUsers().clear();
		UserManager.getInstance().getUsers().add(user);

		UserManager.getInstance().deleteUser("t@t.com");

		assertEquals(0, UserManager.getInstance().getUsers().size());
	}

	@Test
	void testLoginUser() {
		User user = new User();
		user.setProfileInformation("email", "t@t.com");
		user.setProfileInformation("username", "t");
		user.setProfileInformation("password", "1");

		UserManager.getInstance().getUsers().clear();
		UserManager.getInstance().getUsers().add(user);

		UserManager.getInstance().login();

		assertEquals(true, UserManager.getInstance().getCurrentUser().getProfileInformation("username").equals("t"));
	}

}

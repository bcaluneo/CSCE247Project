package user;

/**
 * class for user as child in the system
 * 
 * @author Team Blue
 */
public class Child extends User {
	private User parent;

	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

}
import java.util.ArrayList;

/**
 * 
 * @author Frank Habersham
 * class for user as Adult in the system
 */
public class Adult extends User {
	private ArrayList<User> children;

	/**
	 * @return list of children
	 */
	public ArrayList<User> getChildren() {
		return children;
	}

	/**
	 * method to add children in adult account user 
	 * @param child
	 */
	public void addChild(User child) {
		this.children.add(child);
	}
	
	/**
	 * method to remove children 
	 * @param child
	 */
	public void removeChild(User child) {
		this.children.remove(child);
	}
}
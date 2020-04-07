
/**
 * 
 * @author RaviPatel
 * class for user as child in the system
 */
public class Child  extends User{
	private User parent;

	/**
	 * getters and setters 
	 * @return variable
	 */
	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}
	
}
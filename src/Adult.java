import java.util.ArrayList;

public class Adult extends User {
	private ArrayList<User> children;

	public ArrayList<User> getChildren() {
		return children;
	}

	public void addChild(User child) {
		this.children.add(child);
	}
	
	public void removeChild(User child) {
		this.children.remove(child);
	}
}
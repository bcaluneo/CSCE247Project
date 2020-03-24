import java.util.HashMap;

public abstract class Show {

	private static final int MAX_ATTRIBUTES = 7;

	private HashMap<String, Object> showInformation;

	public Show() {
		this.showInformation = new HashMap<String, Object>(MAX_ATTRIBUTES);

		showInformation.put("name", null); /* String */
		showInformation.put("description", null); /* String */
		showInformation.put("price", null); /* double */
		showInformation.put("ageRating", null); /* Enum (Age) */
		showInformation.put("genre", null); /* Enum (Genre) */
		showInformation.put("inTheaters", null); /* boolean */
		showInformation.put("times", null); /* String[] */
	}

	public Object getShowInformation(String key) {
		return showInformation.get(key);
	}

	public void setShowInformation(String key, Object value) {
		if (showInformation.get(key) == null) {
			System.out.println("error setShowInformation(String, Object): no" +
													"information found for that key.");
			return;
		}

		showInformation.replace(key, value);
	}
}

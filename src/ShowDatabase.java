public class ShowDatabase {

	private static ShowDatabase showDatabase;
	
	public static ShowDatabase getInstance() {
		if (showDatabase == null) {
			showDatabase = new ShowDatabase();
		}
		
		return showDatabase;
	}
	
	private ShowDatabase() {
	}
}
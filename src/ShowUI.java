import java.util.Scanner;

public class ShowUI {

	private Scanner scanner;
	private ShowDatabase showdatabase;
	private UserManager usermanager;

	private String[] options = {"Login", "Create Account", "Search", "Book",
															"Quit"};

	public ShowUI() {
		scanner = new Scanner(System.in);
	}

	public void printOptions() {
		System.out.println("===========================");
		System.out.println("Options:");
		for (int i = 0; i < options.length; i++) {
				String printout = options[i];
				System.out.println(i + ": " + printout);
		}

		System.out.print("Make a selection: ");
	}

	public int getInput() {
		printOptions();
		int selection = scanner.nextInt();
		while (selection < 0 || selection >= options.length) {
			printOptions();
			selection = scanner.nextInt();
		}

		return selection;
	}

	public void run() {
		System.out.println("Show System Started");

		boolean quit = false;
		while (!quit) {
			int selection = getInput();
			switch (selection) {
				case 0:
					selection = getInput();
					break;
				case 1:
					selection = getInput();
					break;
				case 2:
					selection = getInput();
					break;
				case 3:
					selection = getInput();
					break;
				case 4:
					System.out.println("Quitting system...");
					quit = true;
					break;
			}
		}
	}
}

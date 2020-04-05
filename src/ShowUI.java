import java.util.Scanner;

public class ShowUI {

	private Scanner scanner;

	private String[] options = {"Login", "Create Account", "Search/Sort", "Book",
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
				case 0: /* Login */
					selection = getInput();
					break;
				case 1: /* Create Account */
					selection = getInput();
					break;
				case 2: /* Search */
					selection = getInput();
					break;
				case 3: /* Book */
					selection = getInput();

					/* Phase 1: Print out the venues.
						 Phase 2: Select a venue.
						 Phase 3: Select a showing.
						 Phase 4: Select a theater and seat.
						 Phase 5: Initiate the payment.
						 Phase 6: Printout the tickets. */
					break;
				case 4: /* Quit */
					System.out.println("Quitting system...");
					quit = true;
					break;
			}
		}
	}
}

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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

	public void run() {
		System.out.println("Show System Started");

		boolean quit = false;
		while (!quit) {
			printOptions();
			int selection = scanner.nextInt();
			switch (selection) {
				case 0: /* Login */
					break;
				case 1: /* Create Account */
					break;
				case 2: /* Search */
					break;
				case 3: /* Book */
					bookTicket();
					break;
				case 4: /* Quit */
					System.out.println("Quitting system...");
					quit = true;
					break;
			}
		}
	}

	public void bookTicket() {
		System.out.println(VenueDatabase.getInstance().toString());
		int selection = scanner.nextInt();
		Venue venue = VenueDatabase.getInstance().getVenueByIndex(selection);
		System.out.println(ShowDatabase.getInstance().toString());
		selection = scanner.nextInt();
		Show show = ShowDatabase.getInstance().getShowByIndex(selection);
		System.out.println(venue.toString());
		selection = scanner.nextInt();
		Theater theater = venue.getTheater(selection);
		List<Seat> seats = new ArrayList<Seat>();
		char row;
		int column;
		System.out.println("How many seats would you like to book?");
		int seatCount = scanner.nextInt();
		theater.printSeats();
		System.out.println("Select your seats: ");
		while (seatCount != 0) {
			row = scanner.next().charAt(0);
			column = scanner.nextInt();
			seats.add(theater.getSeats()[row-'A'][column]); /* TODO: Change this. */
			seatCount--;
		}

		scanner.nextLine();

		processOrder(venue, theater, show, seats);
	}

	public void processOrder(Venue venue, Theater theater, Show show, List<Seat> seats) {
		System.out.print("Enter Card Holder's Name: ");
		String cardName = scanner.nextLine();
		System.out.print("Enter Card Number (No Dash): ");
		String cardNumber = scanner.nextLine();
		System.out.print("Enter Card Expiration Date (MM/YY): ");
		String cardDate = scanner.nextLine();
		System.out.print("Enter Card Security Code: ");
		String cardCode = scanner.nextLine();
		if (!UserManager.getInstance().getCurrentUser().isDefaultUser()) {
				System.out.println("Would you like to save this payment information? (y/n)");
				char choice = scanner.next().charAt(0);
				if (choice == 'y') {
					System.out.println("Information saved.");
					UserManager.getInstance().getCurrentUser().setPaymentInformation("card-name", cardName);
					UserManager.getInstance().getCurrentUser().setPaymentInformation("card-number", cardNumber);
					UserManager.getInstance().getCurrentUser().setPaymentInformation("card-date", cardDate);
					UserManager.getInstance().getCurrentUser().setPaymentInformation("card-code", cardCode);
				}
		}

		System.out.println("===========================");
		System.out.println("Shopping Cart:");
		System.out.println("Venue: " + venue.getName());
		System.out.println("Theater: " + theater.getTheaterNumber());
		System.out.println("Seats: ");
		for (Seat s : seats) {
			System.out.println("** " + s.getSeatRow() + "" + s.getSeatColumn());
		}

		System.out.println("Would you like to checkout? (y/n)");
		char choice = scanner.next().charAt(0);
		if (choice == 'y') {
			for (Seat s : seats) {
				venue.bookSeat(theater, s.getSeatRow(), s.getSeatColumn());
			}

			UserManager.getInstance().getCurrentUser().bookShow(venue, theater, show, seats);
		}

		System.out.println("Seats successfully booked.");
		theater.printSeats();
	}
}

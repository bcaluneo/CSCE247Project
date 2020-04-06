import java.util.Scanner;
import java.util.List;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;

public class ShowUI {

	private Scanner scanner;

	private String[] options = {"Login", "Create Account", "Search/Sort", "Book",
															"Rate", "Quit"};

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
			int selection = Integer.parseInt(scanner.nextLine());
			switch (selection) {
				case 0: /* Login */
					UserManager.getInstance().loginUser();
					break;
				case 1: /* Create Account */
					UserManager.getInstance().createUser();
					break;
				case 2: /* Search */
					search();
					break;
				case 3: /* Book */
					bookTicket();
					break;
				case 4: /* Rate */
					rate();
					break;
				case 5: /* Quit */
					System.out.println("Quitting system...");
					quit = true;
					break;
			}
		}
	}

	public void search() {
		System.out.println("**Search System Pro**");
		System.out.println("1. Search");
		System.out.println("2. Sort");

		System.out.print("Make a selection: ");
		int selection = Integer.parseInt(scanner.nextLine());
		boolean search = selection == 1 ? true : false;

		System.out.println("Options:");
		System.out.println("1. Name");
		System.out.println("2. Genre");
		System.out.println("3. Age Rating");
		System.out.println("4. List All");

		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());

		switch (selection) {
			case 1:
				if (!search) {
					System.out.println(ShowDatabase.getInstance().sortByName());
				} else {
					System.out.print("Enter show name: ");
					String showName = scanner.nextLine();

					Show show = ShowDatabase.getInstance().getShowByName(showName);
					if (show == null) {
						System.out.println("That show is not in the database.");
					} else {
						System.out.println(show.toString());
					}
				}

				break;
			case 2:
				if (!search) {
					printShowList(ShowDatabase.getInstance().sortByGenre());
				} else {
					System.out.println("Options: ");
					for (int i = 0; i < Genre.values().length; i++) {
						System.out.println((i+1) + ". " + Genre.values()[i]);
					}

					System.out.print("Make a selection: ");
					selection = Integer.parseInt(scanner.nextLine());

					Genre genre = Genre.values()[selection-1];
					if (genre != null) {
						printShowList(ShowDatabase.getInstance().searchByGenre(genre));
					}
				}

				break;
			case 3:
				if (!search) {
					System.out.println(ShowDatabase.getInstance().sortByAgeRating());
				} else {
					System.out.println("Options: ");
					for (int i = 0; i < Age.values().length; i++) {
						System.out.println((i+1) + ". " + Age.values()[i]);
					}

					System.out.print("Make a selection: ");
					selection = Integer.parseInt(scanner.nextLine());

					Age ageRating = Age.values()[selection-1];
					if (ageRating != null) {
						printShowList(ShowDatabase.getInstance().searchByAgeRating(ageRating));
					}
				}

				break;
			case 4:
				System.out.println(ShowDatabase.getInstance().toString());
				break;
		}
	}

	public void printShowList(List<Show> shows) {
		for (Show show : shows) {
			System.out.println(show.toString());
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
	}
	
	
	public void rate() {
		
	//	System.out.println(ShowDatabase.getInstance().toString());

		User user = UserManager.getInstance().getCurrentUser();
		
		for( int i = 0; i < user.getBookedShows().size(); i++) {
			BookedShow show = user.getBookedShows().get(i);
			System.out.println(show.getShow().getShowInformation("name"));
		}			
		int selection = scanner.nextInt();
		
		System.out.println("Rate the movie from 1 to 5 stars: ");
		selection = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the Comment: ");
		String comment = scanner.nextLine();
		
		Review review = new Review(user,comment,selection);
	}
}

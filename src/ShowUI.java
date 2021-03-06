import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import data.DataWriter;
import managers.ShowDatabase;
import managers.UserManager;
import managers.VenueDatabase;
import show.Age;
import show.BookedShow;
import show.Genre;
import show.Review;
import show.Show;
import show.ShowType;
import user.User;
import venue.Seat;
import venue.Theater;
import venue.Venue;

/**
 * The interface for the system.
 * @author Team Blue
 *
 */
public class ShowUI {

	/** The mechanism through which the user is going to input keystrokes into the program. */
	private Scanner scanner;

	/** The list of menu options. */
	private String[] options = {"Login", "Create Account", "Search/Sort", "Book",
															"Rate", "Management", "Quit"};

	/** The list of management menu options. */
	private String[] mgtOptions = {"Add Venue", "Remove Venue", "Edit Venue",
																 "Remove Review", "Modify Account",
																 "Add Show", "Remove Show", "Exit"};
	/**
	 * Initializes the show interface.
	 */
	public ShowUI() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Prints the list of menu options.
	 */
	public void printOptions() {
		System.out.println("===========================");
		System.out.println("Options:");
		for (int i = 0; i < options.length; i++) {
				String printout = options[i];
				System.out.println(i + ": " + printout);
		}

		System.out.print("Make a selection: ");
	}

	/**
	 * Prints the list of management menu options.
	 */
	public void printMgtOptions() {
		System.out.println("===========================");
		System.out.println("Mangement Mode:");
		for (int i = 0; i < mgtOptions.length; i++) {
				String printout = mgtOptions[i];
				System.out.println(i + ": " + printout);
		}

		System.out.print("Make a selection: ");
	}

	/**
	 * Runs the system.
	 */
	public void run() {
		System.out.println("Show System Started");

		boolean quit = false;
		while (!quit) {
			printOptions();
			int selection = Integer.parseInt(scanner.nextLine());
			switch (selection) {
				case 0: /* Login */
					UserManager.getInstance().login();
					break;
				case 1: /* Create Account */
					createAccount();
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
				case 5: /* Management */
					mangementMode();
					break;
				case 6: /* Quit */
					System.out.println("Quitting system...");
					quit = true;
					break;
			}
		}
	}

	/**
	 * Allows staff to make changes to the program database's.
	 */
	public void mangementMode() {
		User user = UserManager.getInstance().getCurrentUser();
		if (!user.isAdmin() && !user.isStaff()) {
			System.out.println("You do not have access to these functions.");
			return;
		}

		boolean quit = false;
		while (!quit) {
			printMgtOptions();
			int selection = Integer.parseInt(scanner.nextLine());
			switch (selection) {
				case 0: /* Add Venue */
					addVenue();
					break;
				case 1: /* Remove Venue */
					removeVenue();
					break;
				case 2: /* Edit Venue */
					editVenue();
					break;
				case 3: /* Remove Review */
					removeReview();
					break;
				case 4: /* Modify Account */
					modifyAccount();
					break;
				case 5: /* Add Show */
					addShow();
					break;
				case 6: /* Remove Show */
					removeShow();
					break;
				case 7: /* Exit */
					quit = true;
					continue;
			}
		}
	}

	/* Management Functions */

	/**
	 * Allows staff to edit venue information.
	 */
	public void editVenue() {
		System.out.println(VenueDatabase.getInstance().toString());
		System.out.print("Make a selection: ");
		int selection = Integer.parseInt(scanner.nextLine());

		Venue venue = VenueDatabase.getInstance().getVenueByIndex(selection);
		System.out.println(venue.toString());

		System.out.println("Options: ");
		System.out.println("1. Change Name");
		System.out.println("2. Change Location");
		System.out.println("3. Quit");

		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());

		switch (selection) {
			case 1:
				System.out.print("Enter New Name: ");
				String newName = scanner.nextLine();
				venue.setName(newName);
				break;
			case 2:
				System.out.print("Enter New Location: ");
				String newLocation = scanner.nextLine();
				venue.setLocation(newLocation);
				break;
			case 3:
				return;
		}

		DataWriter.writeVenues();
	}

	/**
	 * Allows staff to add venues.
	 */
	public void addVenue() {
		System.out.print("Enter Venue Name: ");
		String venueName = scanner.nextLine();
		System.out.print("Enter Venue Location: ");
		String venueLocation = scanner.nextLine();

		Venue venue = new Venue(venueName, venueLocation);
		VenueDatabase.getInstance().addVenue(venue);

		DataWriter.writeVenues();
	}

	/**
	 * Allows staff to remove a venue.
	 */
	public void removeVenue() {
		System.out.println(VenueDatabase.getInstance().toString());
		System.out.print("Make a selection: ");
		int selection = Integer.parseInt(scanner.nextLine());

		System.out.println("Are you sure you want to delete this? (y/n)");
		char choice = scanner.nextLine().charAt(0);
		if (choice == 'n') {
			return;
		} else if (choice == 'y') {
			Venue venue = VenueDatabase.getInstance().getVenueByIndex(selection);
			VenueDatabase.getInstance().removeVenue(venue);
		}

		DataWriter.writeVenues();
	}

	/**
	 * Allows admins to remove reviews from a show.
	 */
	public void removeReview() {
		if (!UserManager.getInstance().getCurrentUser().isAdmin()) {
			System.out.println("You do not have access to this function.");
		}

		System.out.println(ShowDatabase.getInstance().toString());

		System.out.print("Make a selection: ");
		int selection = Integer.parseInt(scanner.nextLine());

		Show show = ShowDatabase.getInstance().getShowByIndex(selection);
		System.out.println(show.toString(true));

		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());

		Review review = show.getReviewByIndex(selection);
		show.deleteReview(review);

		System.out.println("Review successfully deleted.");

		DataWriter.writeShows();
	}

	/**
	 * Allows admins to elevate accounts to either staff or admins.
	 */
	public void modifyAccount() {
		if (!UserManager.getInstance().getCurrentUser().isAdmin()) {
			System.out.println("You do not have access to this function.");
		}

		System.out.println(UserManager.getInstance().toString());
		System.out.print("Make a selection: ");
		int selection = Integer.parseInt(scanner.nextLine());

		User user = UserManager.getInstance().getUserByIndex(selection);
		if (user.isStaff() || user.isAdmin()) {
			System.out.println("User is already staff.");
			return;
		}

		System.out.println("Options:");
		System.out.println("1. Toggle Staff Access");
		System.out.println("2. Toggle Admin Access");

		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());

		if (selection == 1) {
			UserManager.getInstance().toggleStaffAccount(user);
			if (user.isStaff()) {
				System.out.println("User " + user.getProfileInformation("username") +
						 " successfully promoted to staff.");
			} else {
				System.out.println("User " + user.getProfileInformation("username") +
						 " successfully demoted from staff.");
			}
		} else if (selection == 2) {
			UserManager.getInstance().toggleAdminAccount(user);
			if (user.isAdmin()) {
				System.out.println("User " + user.getProfileInformation("username") +
						 " successfully promoted to admin.");
			} else {
				System.out.println("User " + user.getProfileInformation("username") +
						 " successfully demoted from admin.");
			}
		}

		DataWriter.writeUsers();
	}

	/**
	 * Allows staff to add a showing.
	 */
	public void addShow() {
		System.out.print("Enter Show Name: ");
		String showName = scanner.nextLine();
		System.out.print("Enter Show Description: ");
		String showDescription = scanner.nextLine();
		System.out.print("Enter Show Price: ");
		double showPrice = Double.parseDouble(scanner.nextLine());
		System.out.println("Show Type: ");
		for (int i = 0; i < ShowType.values().length; i++) {
			System.out.println((i+1) + ". " + ShowType.values()[i]);
		}

		System.out.print("Make a selection: ");
		int selection = Integer.parseInt(scanner.nextLine());

		ShowType showType = ShowType.values()[selection-1];

		System.out.println("Age Ratings: ");
		for (int i = 0; i < Age.values().length; i++) {
			System.out.println((i+1) + ". " + Age.values()[i]);
		}

		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());

		Age age = Age.values()[selection-1];

		System.out.println("Genres: ");
		for (int i = 0; i < Genre.values().length; i++) {
			System.out.println((i+1) + ". " + Genre.values()[i]);
		}

		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());

		Genre genre = Genre.values()[selection-1];

		System.out.println("Enter Total Show Times: ");
		int showCount = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter times (MMM DD HH:MM):");
		List<String> times = new ArrayList<String>();
		while (showCount > 0) {
			times.add(scanner.nextLine());
			showCount--;
		}

		Show newShow = new Show();
		newShow.setShowInformation("name", showName);
		newShow.setShowInformation("showType", showType);
		newShow.setShowInformation("description", showDescription);
		newShow.setShowInformation("price", showPrice);
		newShow.setShowInformation("age", age);
		newShow.setShowInformation("genre", genre);
		newShow.setShowInformation("inTheaters", true);
		newShow.setShowInformation("times", times);

		ShowDatabase.getInstance().addShow(newShow);
		System.out.println("Show successfully added.");
		DataWriter.writeShows();
	}

	/**
	 * Allows staff to remove a showing.
	 */
	public void removeShow() {
		System.out.println(ShowDatabase.getInstance().toString());

		System.out.print("Make a selection: ");
		int selection = Integer.parseInt(scanner.nextLine());

		System.out.println("Are you sure you want to delete this? (y/n)");
		char choice = scanner.nextLine().charAt(0);
		if (choice == 'n') {
			return;
		} else if (choice == 'y') {
			Show show = ShowDatabase.getInstance().getShowByIndex(selection);
			ShowDatabase.getInstance().removeShow(show);
		}

		DataWriter.writeShows();
	}

	/* End Management Functions */

	/**
	 * Allows the creation of a user account.
	 */
	public void createAccount() {
		System.out.println("Are you a child or adult? Enter \"child\" or \"adult\"");
		String person = scanner.nextLine();
		if(person.equals("child")) {
			System.out.println("Your parent must enter their loggin information first.");
			User parent = UserManager.getInstance().loginUser();
			if(parent!=null) {
				System.out.println("Parent information confimed");
				UserManager.getInstance().createChildAccount(parent);
			}
		} else if(person.equals("adult")) {
			UserManager.getInstance().createAdultAccount();
		}

		DataWriter.writeUsers();
	}

	/**
	 * Brings up the search database interface.
	 */
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
						System.out.println(show.toString(true));
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
					System.out.println(ShowDatabase.getInstance().sortByAge());
				} else {
					System.out.println("Options: ");
					for (int i = 0; i < Age.values().length; i++) {
						System.out.println((i+1) + ". " + Age.values()[i]);
					}

					System.out.print("Make a selection: ");
					selection = Integer.parseInt(scanner.nextLine());

					Age age = Age.values()[selection-1];
					if (age != null) {
						printShowList(ShowDatabase.getInstance().searchByAgeRating(age));
					}
				}

				break;
			case 4:
				System.out.println(ShowDatabase.getInstance().toString());
				break;
		}
	}

	/**
	 * Helper function to print a list of shows.
	 * @param shows
	 */
	public void printShowList(List<Show> shows) {
		for (Show show : shows) {
			System.out.println(show.toString(false));
		}
	}

	/**
	 * Allows users of the system to book tickets.
	 */
	public void bookTicket() {
		System.out.println(VenueDatabase.getInstance().toString());
		System.out.print("Make a selection: ");
		int selection = Integer.parseInt(scanner.nextLine());
		Venue venue = VenueDatabase.getInstance().getVenueByIndex(selection);
		System.out.println(ShowDatabase.getInstance().toString());
		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());
		Show show = ShowDatabase.getInstance().getShowByIndex(selection);
		System.out.println(venue.toString());
		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());
		Theater theater = venue.getTheater(selection);

		List<String> times = (ArrayList<String>) show.getShowInformation("times");
		System.out.println("Available times: ");
		for (int i = 0; i < times.size(); i++) {
			System.out.println("\t" + i + ": " + times.get(i));
		}

		System.out.print("Make a selection: ");
		selection = Integer.parseInt(scanner.nextLine());
		String time = times.get(selection);

		List<Seat> seats = new ArrayList<Seat>();
		System.out.println("How many seats would you like to book?");
		int seatCount = Integer.parseInt(scanner.nextLine());
		theater.printSeats();
		System.out.println("Select your seats: ");
		String seat = "";
		while (seatCount != 0) {
			seat = scanner.nextLine();
			char row = seat.charAt(0);
			int column = Integer.parseInt(""+seat.charAt(1));
			seats.add(theater.getSeats()[row-'A'][column]);
			seatCount--;
		}

		processOrder(venue, theater, show, seats, time);
	}

	/**
	 * Processes a ticket order.
	 * @param venue
	 * @param theater
	 * @param show
	 * @param seats
	 */
	public void processOrder(Venue venue, Theater theater, Show show, List<Seat> seats, String time) {
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
				char choice = scanner.nextLine().charAt(0);
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
		char choice = scanner.nextLine().charAt(0);
		if (choice == 'y') {
			for (Seat s : seats) {
				venue.bookSeat(theater, s.getSeatRow(), s.getSeatColumn());
			}

			UserManager.getInstance().getCurrentUser().bookShow(venue, theater, show, seats, time);
		}

		System.out.println("Seats successfully booked.");
		theater.printSeats();
	}

	/**
	 * Allows users to rate a show with a numerical rating and a short description.
	 */
	public void rate() {
		User user = UserManager.getInstance().getCurrentUser();
		if (user.getBookedShows().size() == 0) return;

		for( int i = 0; i < user.getBookedShows().size(); i++) {
			BookedShow show = user.getBookedShows().get(i);
			System.out.println(i + ": " + show.getShow().getShowInformation("name"));
		}

		int selection = Integer.parseInt(scanner.nextLine());
		BookedShow toReview = user.getBookedShows().get(selection);
		System.out.println("Rate the movie from 1 to 5 stars: ");
		selection = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the Comment: ");
		String comment = scanner.nextLine();

		Review review = new Review(user, comment, selection);
		toReview.getShow().addReview(review);

		DataWriter.writeShows();
	}
}

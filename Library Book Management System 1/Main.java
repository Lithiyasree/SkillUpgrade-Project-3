
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.util.*;



public class Main {
    private static Map<String, Book> books = new HashMap<>();
    private static Map<String, Patron> patrons = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();

        System.out.println("Welcome to the Library System!");

        System.out.println("\nMENU:");
        System.out.println("1. Add Book");
        System.out.println("2. Add Patron");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Save Data");
        System.out.println("6. Exit");
        while (true) {
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
					addBook();
					break;
				case 2:
					addPatron();
					break;

                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
					saveData();
					break;

                case 6:
                    saveData();
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


    //EXISTING BOOKS & PATRONS
     private static void initializeData() {
        // Add some books to the library
        books.put("The Great Gatsby", new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        books.put("To Kill a Mockingbird", new Book("To Kill a Mockingbird", "Harper Lee"));

        // Add some patrons to the library
        patrons.put("Alice", new Patron("Alice"));
        patrons.put("Bob", new Patron("Bob"));
    }


    //BORROW BOOK
    private static void borrowBook() {
        System.out.print("\nEnter patron name: ");
        String patronName = scanner.nextLine();

        if (!patrons.containsKey(patronName)) {
            System.out.println("Patron not found.");
            return;
        }

        Patron patron = patrons.get(patronName);

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        if (!books.containsKey(bookTitle)) {
            System.out.println("Book not found.");
            return;
        }

        Book book = books.get(bookTitle);

        if (book.isBorrowed()) {
            System.out.println("Book is already borrowed by " + book.getBorrowedBy());
            return;
        }

        LocalDate transactionDate = LocalDate.now();
        LocalDate dueDate = transactionDate.plusDays(14);
        Transaction transaction = new Transaction(patron, book, transactionDate, dueDate);

        book.borrowBook(patronName);

        System.out.println("\nTransaction Successful.");
        transaction.printTransaction();
    }


    //RETURN BOOK
    private static void returnBook() {
        System.out.print("\nEnter patron name: ");
        String patronName = scanner.nextLine();

        if (!patrons.containsKey(patronName)) {
            System.out.println("Patron not found.");
            return;
        }

        Patron patron = patrons.get(patronName);

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        if (!books.containsKey(bookTitle)) {
            System.out.println("Book not found.");
            return;
        }

        Book book = books.get(bookTitle);

        if (!book.isBorrowed() || !book.getBorrowedBy().equals(patronName)) {
            System.out.println("Book is not borrowed by this patron.");
            return;
        }

        System.out.print("Enter return date (YYYY-MM-DD): ");
        LocalDate returnDate;
        try {
            returnDate = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        LocalDate dueDate = LocalDate.now().plusDays(14);
        Transaction transaction = new Transaction(patron, book, LocalDate.now(), dueDate);
        transaction.returnBook(returnDate);

        System.out.println("\nReturn Successful.");
        transaction.printTransaction();
    }


    //ADD BOOK
    private static void addBook() {
		System.out.print("\nEnter book title: ");
		String title = scanner.nextLine();

		System.out.print("Enter book author: ");
		String author = scanner.nextLine();

		Book book = new Book(title, author);
		books.put(title, book);
		System.out.println("Book added successfully!");
	}


    //ADD PATRON
	private static void addPatron() {
		System.out.print("\nEnter patron name: ");
		String name = scanner.nextLine();

		Patron patron = new Patron(name);
		patrons.put(name, patron);
		System.out.println("Patron added successfully!");
	}


    //SAVE DATA
    private static void saveData() {
		try {
			FileOutputStream fileOut = new FileOutputStream("book_data.txt");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(books);
			objectOut.writeObject(patrons);
			objectOut.close();
			System.out.println("Data saved successfully!");
		} catch (IOException e) {
			System.err.println("Error saving data: " + e.getMessage());
		}
	}


}







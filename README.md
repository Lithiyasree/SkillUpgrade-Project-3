# LIBRARY MANAGEMENT SYSTEM
JAVA


This is a simple Library Management System implemented in Java that allows users to perform basic library operations like adding books, adding patrons, borrowing and returning books, and saving data. The system supports handling transactions such as fines for overdue books.

## Features:
- **Add Book:** Allows the admin to add new books to the library system.
- **Add Patron:** Allows the admin to add new patrons (library members).
- **Borrow Book:** Allows patrons to borrow books, with due dates and tracking of borrowed books.
- **Return Book:** Allows patrons to return books with overdue fines calculated.
- **Save Data:** Saves the library data (books and patrons) to a file to preserve the data even after the program exits.

## Components:
1. **Books:** Each book contains information about the title, author, and whether it has been borrowed.
2. **Patrons:** Each patron represents a library member with their name.
3. **Transactions:** Each transaction tracks when a patron borrows and returns a book, including due dates and overdue fines.

## Classes:
- `Main`: The entry point of the application. Handles the main menu and invokes actions for borrowing, returning, adding books, and patrons.
- `Book`: Represents a book in the library. Provides functionality for borrowing and returning books.
- `Patron`: Represents a library member. Allows storing and retrieving patron information.
- `Transaction`: Represents a borrowing transaction. Tracks the transaction details, including overdue fines.


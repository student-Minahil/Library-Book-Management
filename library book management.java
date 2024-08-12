import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean available;
    private String borrower;
    private String returnDate;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.available = true;
        this.borrower = null;
        this.returnDate = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowBook(String borrower, String returnDate) {
        this.borrower = borrower;
        this.returnDate = returnDate;
        this.available = false;
    }

    public void returnBook() {
        this.borrower = null;
        this.returnDate = null;
        this.available = true;
    }

    public String getBorrower() {
        return borrower;
    }

    public String getReturnDate() {
        return returnDate;
    }
}

class Library {
    private Map<String, Book> books;

    public Library() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getISBN(), book);
    }

    public Book searchBook(String ISBN) {
        return books.get(ISBN);
    }

    public void checkOutBook(String ISBN, String borrower, String returnDate) {
        Book book = books.get(ISBN);
        if (book != null && book.isAvailable()) {
            book.borrowBook(borrower, returnDate);
            System.out.println("Book checked out successfully!");
        } else {
            System.out.println("Book is not available for checkout.");
        }
    }

    public void returnBook(String ISBN) {
        Book book = books.get(ISBN);
        if (book != null && !book.isAvailable()) {
            book.returnBook();
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book is already available or not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Harry Potter", "J.K. Rowling", "9780545010221");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084");

        library.addBook(book1);
        library.addBook(book2);

        library.checkOutBook("9780545010221", "Alice", "2024-08-15");
        library.checkOutBook("9780061120084", "Bob", "2024-08-20");

        library.returnBook("9780545010221");

        Book searchedBook = library.searchBook("9780061120084");
        if (searchedBook != null) {
            System.out.println("Book Found: " + searchedBook.getTitle() + " by " + searchedBook.getAuthor());
        } else {
            System.out.println("Book not found.");
        }
    }
}
import java.util.*;
import java.io.*;

class Book
{
    String title;
    String author;
    boolean checkedOut;

    public Book(String title, String author, boolean checkedOut)
    {
        this.title = title;
        this.author = author;
        this.checkedOut = checkedOut;
    }
}

class LibraryCatalog
{
    ArrayList<Book> books;

    public LibraryCatalog()
    {
        books = new ArrayList<>();
    }

    public void addBook(Book book)
    {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void searchByTitle(String title)
    {
        boolean found = false;
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title))
            {
                System.out.println("Book found: ");
                displayBookDetails(book);
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("Book not found with title: " + title);
        }
    }

    public void searchByAuthor(String author)
    {
        boolean found = false;
        for (Book book : books) {
            if (book.author.equalsIgnoreCase(author))
            {
                System.out.println("Book found: ");
                displayBookDetails(book);
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("Book not found with author: " + author);
        }
    }

    public void checkOutBook(String title)
    {
        for (Book book : books)
        {
            if (book.title.equalsIgnoreCase(title))
            {
                if (book.checkedOut)
                {
                    System.out.println("Sorry, the book is already checked out.");
                } else
                {
                    book.checkedOut = true;
                    System.out.println("Book checked out successfully.");
                }
                return;
            }
        }
        System.out.println("Book not found with title: " + title);
    }

    public void returnBook(String title)
    {
        for (Book book : books)
        {
            if (book.title.equalsIgnoreCase(title))
            {
                if (!book.checkedOut)
                {
                    System.out.println("The book is already available in the library.");
                } else
                {
                    book.checkedOut = false;
                    System.out.println("Book returned successfully.");
                }
                return;
            }
        }
        System.out.println("Book not found with title: " + title);
    }

    public void displayBookDetails(Book book)
    {
        System.out.println("Title: " + book.title);
        System.out.println("Author: " + book.author);
        System.out.println("Status: " + (book.checkedOut ? "Checked Out" : "Available"));
    }

    public void displayAvailableBooks()
    {
        HashMap<String, Integer> availableBooks = new HashMap<>();
        for (Book book : books) {

            if (!book.checkedOut)
            {
                String key = book.title + " by " + book.author;
                availableBooks.put(key, availableBooks.getOrDefault(key, 0) + 1);
            }
        }
        if (availableBooks.isEmpty())
        {
            System.out.println("No available books.");
        } else
        {
            System.out.println("Available Books:");
            for (Map.Entry<String, Integer> entry : availableBooks.entrySet())
            {
                System.out.println(entry.getKey() + " - Available copies: " + entry.getValue());
            }
        }
    }
}
class LibraryManagementSystem
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        LibraryCatalog catalog = new LibraryCatalog();

        while (true)
        {
            System.out.println("\n1. Add Book");
            System.out.println("2. Search by Title");
            System.out.println("3. Search by Author");
            System.out.println("4. Check Out Book");
            System.out.println("5. Return Book");
            System.out.println("6. Show Available Books with Title and Author");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice)
            {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    catalog.addBook(new Book(title, author, false));
                    break;
                case 2:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    catalog.searchByTitle(searchTitle);
                    break;
                case 3:
                    System.out.print("Enter author to search: ");
                    String searchAuthor = scanner.nextLine();
                    catalog.searchByAuthor(searchAuthor);
                    break;
                case 4:
                    System.out.print("Enter title to check out: ");
                    String checkOutTitle = scanner.nextLine();
                    catalog.checkOutBook(checkOutTitle);
                    break;
                case 5:
                    System.out.print("Enter title to return: ");
                    String returnTitle = scanner.nextLine();
                    catalog.returnBook(returnTitle);
                    break;
                case 6:
                    catalog.displayAvailableBooks();
                    break;
                case 7:
                    System.out.println("Thank you for Visiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}


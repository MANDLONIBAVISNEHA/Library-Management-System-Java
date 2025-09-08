import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
}

class Member {
    int memberId;
    String name;

    Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("✅ Book added: " + book.title);
    }

    public void registerMember(Member member) {
        members.add(member);
        System.out.println("✅ Member registered: " + member.name);
    }

    public void issueBook(int bookId, int memberId) {
        Book book = findBook(bookId);
        if (book != null && !book.isIssued) {
            book.isIssued = true;
            System.out.println("📖 Book '" + book.title + "' issued to Member ID: " + memberId);
        } else {
            System.out.println("❌ Book not available.");
        }
    }

    public void returnBook(int bookId) {
        Book book = findBook(bookId);
        if (book != null && book.isIssued) {
            book.isIssued = false;
            System.out.println("📘 Book '" + book.title + "' returned successfully.");
        } else {
            System.out.println("❌ Invalid return.");
        }
    }

    public void viewBooks() {
        System.out.println("\n📚 Books in Library:");
        for (Book b : books) {
            System.out.println(b.id + ". " + b.title + " by " + b.author + (b.isIssued ? " (Issued)" : " (Available)"));
        }
    }

    private Book findBook(int bookId) {
        for (Book b : books) {
            if (b.id == bookId) return b;
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n=== LIBRARY MENU ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Books");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                case 2:
                    System.out.print("Enter Member ID: ");
                    int mid = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Member Name: ");
                    String mname = sc.nextLine();
                    library.registerMember(new Member(mid, mname));
                    break;
                case 3:
                    System.out.print("Enter Book ID to Issue: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter Member ID: ");
                    int memId = sc.nextInt();
                    library.issueBook(bookId, memId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to Return: ");
                    int rId = sc.nextInt();
                    library.returnBook(rId);
                    break;
                case 5:
                    library.viewBooks();
                    break;
                case 6:
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}

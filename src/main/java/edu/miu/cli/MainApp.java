package edu.miu.cli;

import edu.miu.dto.BookDto;
import edu.miu.dto.MemberDto;
import edu.miu.model.Book;
import edu.miu.model.DomainException;
import edu.miu.model.Member;
import edu.miu.repository.BookRepository;
import edu.miu.repository.InMemoryBookRepository;
import edu.miu.repository.InMemoryMemberRepository;
import edu.miu.repository.MemberRepository;
import edu.miu.service.BorrowingService;
import edu.miu.service.LibraryService;
import edu.miu.service.LibraryStatisticsService;
import edu.miu.utility.JsonConverter;

import java.util.Scanner;

public class MainApp {
    private final LibraryService libraryService;
    private final JsonConverter jsonConverter;
    private final Scanner scanner;

    public MainApp(LibraryService libraryService, JsonConverter jsonConverter, Scanner scanner) {
        this.libraryService = libraryService;
        this.jsonConverter = jsonConverter;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        BookRepository bookRepository = new InMemoryBookRepository();
        MemberRepository memberRepository = new InMemoryMemberRepository();
        LibraryService libraryService = new LibraryService(
                bookRepository,
                memberRepository,
                new BorrowingService(),
                new LibraryStatisticsService(bookRepository));

        seedData(libraryService);
        new MainApp(libraryService, new JsonConverter(), new Scanner(System.in)).run();
    }

    public void run() {
        System.out.println("Library Management System");
        boolean running = true;

        while (running) {
            printMenu();
            String choice = prompt("Choose an option: ");

            try {
                if ("1".equals(choice)) {
                    listBooks();
                } else if ("2".equals(choice)) {
                    listMembers();
                } else if ("3".equals(choice)) {
                    borrowBook();
                } else if ("4".equals(choice)) {
                    returnBook();
                } else if ("5".equals(choice)) {
                    showTotalBooksCount();
                } else if ("6".equals(choice)) {
                    printBooksJson();
                } else if ("7".equals(choice)) {
                    printMembersJson();
                } else if ("0".equals(choice)) {
                    running = false;
                } else {
                    System.out.println("Invalid option.");
                }
            } catch (DomainException | IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }

        System.out.println("Goodbye.");
    }

    private static void seedData(LibraryService libraryService) {
        libraryService.registerMember(new Member("M-1", "Jane Doe", "jane@example.com", "555-0100"));
        libraryService.registerMember(new Member("M-2", "John Smith", "john@example.com", "555-0101"));

        libraryService.registerBook(new Book("B-1", "Domain-Driven Design", "Eric Evans", 2010, 100));
        libraryService.registerBook(new Book("B-2", "Clean Code", "Robert C. Martin", 2018, 50));
        libraryService.registerBook(new Book("B-3", "Modern Java", "Sample Author", 2024, 3));
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. List books");
        System.out.println("2. List members");
        System.out.println("3. Borrow book");
        System.out.println("4. Return book");
        System.out.println("5. Show total books count");
        System.out.println("6. Export books as JSON");
        System.out.println("7. Export members as JSON");
        System.out.println("0. Exit");
    }

    private void listBooks() {
        for (BookDto book : libraryService.listBookDtos()) {
            String borrowedBy = book.getBorrowedByMemberId() == null ? "available" : book.getBorrowedByMemberId();
            System.out.println(book.getBookId()
                    + " | " + book.getTitle()
                    + " | " + book.getAuthor()
                    + " | " + book.getPublishedYear()
                    + " | " + book.getCategory()
                    + " | borrowed: " + borrowedBy);
        }
    }

    private void listMembers() {
        for (MemberDto member : libraryService.listMemberDtos()) {
            System.out.println(member.getMemberId()
                    + " | " + member.getFullName()
                    + " | " + member.getEmail()
                    + " | borrowed books: " + member.getBorrowedBookIds());
        }
    }

    private void borrowBook() {
        String memberId = prompt("Member ID: ");
        String bookId = prompt("Book ID: ");

        libraryService.borrowBook(memberId, bookId);
        System.out.println("Book borrowed.");
    }

    private void returnBook() {
        String memberId = prompt("Member ID: ");
        String bookId = prompt("Book ID: ");

        libraryService.returnBook(memberId, bookId);
        System.out.println("Book returned.");
    }

    private void showTotalBooksCount() {
        System.out.println("Total books count: " + libraryService.getTotalBooksCount());
    }

    private void printBooksJson() {
        System.out.println(jsonConverter.booksToJson(libraryService.listBookDtos()));
    }

    private void printMembersJson() {
        System.out.println(jsonConverter.membersToJson(libraryService.listMemberDtos()));
    }

    private String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}

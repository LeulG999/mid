package edu.miu.model;

import java.time.Year;
import java.util.Objects;

public class Book {
    private final String bookId;
    private final String title;
    private final String author;
    private final int publishedYear;
    private int timesBorrowed;
    private String memberId;

    public Book(String bookId, String title, String author, int publishedYear, int timesBorrowed) {
        if (isBlank(bookId)) {
            throw new IllegalArgumentException("bookId is required");
        }
        if (isBlank(title)) {
            throw new IllegalArgumentException("title is required");
        }
        if (isBlank(author)) {
            throw new IllegalArgumentException("author is required");
        }
        if (publishedYear <= 0) {
            throw new IllegalArgumentException("publishedYear must be positive");
        }
        if (timesBorrowed < 0) {
            throw new IllegalArgumentException("timesBorrowed cannot be negative");
        }

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.timesBorrowed = timesBorrowed;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    public String getMemberId() {
        return memberId;
    }

    public boolean isBorrowed() {
        return memberId != null;
    }

    public boolean isBorrowedBy(String memberId) {
        return Objects.equals(this.memberId, memberId);
    }

    public BookCategory getCategory() {
        return getCategory(Year.now().getValue());
    }

    public BookCategory getCategory(int currentYear) {
        int age = currentYear - publishedYear;

        if (age >= 10 && timesBorrowed >= 100) {
            return BookCategory.PREMIUM;
        }
        if (age >= 5 && timesBorrowed >= 50) {
            return BookCategory.POPULAR;
        }
        return BookCategory.STANDARD;
    }

    public void borrowBy(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("member is required");
        }
        if (isBorrowed() && !isBorrowedBy(member.getMemberId())) {
            throw new DomainException("Book is already borrowed by another member");
        }
        if (!isBorrowed()) {
            memberId = member.getMemberId();
            timesBorrowed++;
        }
    }

    public void returnBy(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("member is required");
        }
        if (!isBorrowed()) {
            throw new DomainException("Book is not currently borrowed");
        }
        if (!isBorrowedBy(member.getMemberId())) {
            throw new DomainException("Book can only be returned by the borrowing member");
        }
        memberId = null;
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

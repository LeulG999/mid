package edu.miu.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.miu.model.Book;

@JsonPropertyOrder({
        "bookId",
        "title",
        "author",
        "publishedYear",
        "timesBorrowed",
        "category",
        "borrowedByMemberId"
})
public class BookDto {
    private final String bookId;
    private final String title;
    private final String author;
    private final int publishedYear;
    private final int timesBorrowed;
    private final String category;
    private final String borrowedByMemberId;

    public BookDto(
            String bookId,
            String title,
            String author,
            int publishedYear,
            int timesBorrowed,
            String category,
            String borrowedByMemberId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.timesBorrowed = timesBorrowed;
        this.category = category;
        this.borrowedByMemberId = borrowedByMemberId;
    }

    public static BookDto from(Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishedYear(),
                book.getTimesBorrowed(),
                book.getCategory().name(),
                book.getMemberId());
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

    public String getCategory() {
        return category;
    }

    public String getBorrowedByMemberId() {
        return borrowedByMemberId;
    }
}

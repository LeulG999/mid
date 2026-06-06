package edu.miu.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Member {
    private final String memberId;
    private final String fullName;
    private final String email;
    private final String phoneNumber;
    private final Set<String> borrowedBookIds = new LinkedHashSet<String>();

    public Member(String memberId, String fullName, String email, String phoneNumber) {
        if (isBlank(memberId)) {
            throw new IllegalArgumentException("memberId is required");
        }
        if (isBlank(fullName)) {
            throw new IllegalArgumentException("fullName is required");
        }
        if (isBlank(email)) {
            throw new IllegalArgumentException("email is required");
        }
        if (isBlank(phoneNumber)) {
            throw new IllegalArgumentException("phoneNumber is required");
        }

        this.memberId = memberId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<String> getBorrowedBookIds() {
        return Collections.unmodifiableSet(borrowedBookIds);
    }

    public boolean hasBorrowed(String bookId) {
        return borrowedBookIds.contains(bookId);
    }

    public void borrow(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book is required");
        }
        book.borrowBy(this);
        borrowedBookIds.add(book.getBookId());
    }

    public void returnBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book is required");
        }
        book.returnBy(this);
        borrowedBookIds.remove(book.getBookId());
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

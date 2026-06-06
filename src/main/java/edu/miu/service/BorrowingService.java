package edu.miu.service;

import edu.miu.model.Book;
import edu.miu.model.Member;

public class BorrowingService {
    public void borrowBook(Member member, Book book) {
        requireMember(member);
        requireBook(book);
        member.borrow(book);
    }

    public void returnBook(Member member, Book book) {
        requireMember(member);
        requireBook(book);
        member.returnBook(book);
    }

    private void requireMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("member is required");
        }
    }

    private void requireBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book is required");
        }
    }
}

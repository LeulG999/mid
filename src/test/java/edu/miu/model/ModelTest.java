package edu.miu.model;

import junit.framework.TestCase;

public class ModelTest extends TestCase {
    public void testBookCategoryIsPremiumBeforePopular() {
        Book book = new Book("B-1", "Domain-Driven Design", "Eric Evans", 2010, 100);

        assertEquals(BookCategory.PREMIUM, book.getCategory(2026));
    }

    public void testBookCategoryIsPopularWhenOldAndBorrowedOften() {
        Book book = new Book("B-2", "Clean Code", "Robert C. Martin", 2018, 50);

        assertEquals(BookCategory.POPULAR, book.getCategory(2026));
    }

    public void testBookDefaultsToStandardWhenItDoesNotMeetHigherCategoryRules() {
        Book book = new Book("B-3", "New Book", "Author", 2024, 0);

        assertEquals(BookCategory.STANDARD, book.getCategory(2026));
    }

    public void testMemberCanBorrowAndReturnBook() {
        Member member = new Member("M-1", "Jane Doe", "jane@example.com", "555-0100");
        Book book = new Book("B-4", "Refactoring", "Martin Fowler", 1999, 10);

        member.borrow(book);

        assertTrue(book.isBorrowedBy("M-1"));
        assertTrue(member.hasBorrowed("B-4"));
        assertEquals(11, book.getTimesBorrowed());

        member.returnBook(book);

        assertFalse(book.isBorrowed());
        assertFalse(member.hasBorrowed("B-4"));
    }

    public void testBookCannotBeBorrowedByTwoMembersAtTheSameTime() {
        Member firstMember = new Member("M-1", "Jane Doe", "jane@example.com", "555-0100");
        Member secondMember = new Member("M-2", "John Doe", "john@example.com", "555-0101");
        Book book = new Book("B-5", "Patterns", "Author", 1994, 25);

        firstMember.borrow(book);

        try {
            secondMember.borrow(book);
            fail("Expected a DomainException");
        } catch (DomainException expected) {
            assertEquals("Book is already borrowed by another member", expected.getMessage());
        }
    }
}

package edu.miu.service;

import edu.miu.model.Book;
import edu.miu.model.Member;
import edu.miu.repository.BookRepository;
import edu.miu.repository.InMemoryBookRepository;
import edu.miu.repository.InMemoryMemberRepository;
import edu.miu.repository.MemberRepository;
import junit.framework.TestCase;

public class LibraryServiceTest extends TestCase {
    public void testBorrowBookUpdatesBookAndMemberThroughRepositories() {
        BookRepository bookRepository = new InMemoryBookRepository();
        MemberRepository memberRepository = new InMemoryMemberRepository();
        LibraryService libraryService = createLibraryService(bookRepository, memberRepository);

        libraryService.registerMember(new Member("M-1", "Jane Doe", "jane@example.com", "555-0100"));
        libraryService.registerBook(new Book("B-1", "Domain-Driven Design", "Eric Evans", 2010, 100));

        libraryService.borrowBook("M-1", "B-1");

        assertTrue(bookRepository.findById("B-1").get().isBorrowedBy("M-1"));
        assertTrue(memberRepository.findById("M-1").get().hasBorrowed("B-1"));
        assertEquals(1, libraryService.getTotalBooksCount());
    }

    public void testReturnBookUpdatesBookAndMemberThroughRepositories() {
        BookRepository bookRepository = new InMemoryBookRepository();
        MemberRepository memberRepository = new InMemoryMemberRepository();
        LibraryService libraryService = createLibraryService(bookRepository, memberRepository);

        libraryService.registerMember(new Member("M-1", "Jane Doe", "jane@example.com", "555-0100"));
        libraryService.registerBook(new Book("B-1", "Domain-Driven Design", "Eric Evans", 2010, 100));
        libraryService.borrowBook("M-1", "B-1");

        libraryService.returnBook("M-1", "B-1");

        assertFalse(bookRepository.findById("B-1").get().isBorrowed());
        assertFalse(memberRepository.findById("M-1").get().hasBorrowed("B-1"));
    }

    public void testServiceCanReturnDtosForCliAndJsonOutput() {
        BookRepository bookRepository = new InMemoryBookRepository();
        MemberRepository memberRepository = new InMemoryMemberRepository();
        LibraryService libraryService = createLibraryService(bookRepository, memberRepository);

        libraryService.registerMember(new Member("M-1", "Jane Doe", "jane@example.com", "555-0100"));
        libraryService.registerBook(new Book("B-1", "Domain-Driven Design", "Eric Evans", 2010, 100));

        assertEquals("B-1", libraryService.listBookDtos().get(0).getBookId());
        assertEquals("M-1", libraryService.listMemberDtos().get(0).getMemberId());
    }

    private LibraryService createLibraryService(BookRepository bookRepository, MemberRepository memberRepository) {
        return new LibraryService(
                bookRepository,
                memberRepository,
                new BorrowingService(),
                new LibraryStatisticsService(bookRepository));
    }
}

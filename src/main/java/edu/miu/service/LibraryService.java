package edu.miu.service;

import edu.miu.dto.BookDto;
import edu.miu.dto.MemberDto;
import edu.miu.model.Book;
import edu.miu.model.DomainException;
import edu.miu.model.Member;
import edu.miu.repository.BookRepository;
import edu.miu.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BorrowingService borrowingService;
    private final LibraryStatisticsService statisticsService;

    public LibraryService(
            BookRepository bookRepository,
            MemberRepository memberRepository,
            BorrowingService borrowingService,
            LibraryStatisticsService statisticsService) {
        if (bookRepository == null) {
            throw new IllegalArgumentException("bookRepository is required");
        }
        if (memberRepository == null) {
            throw new IllegalArgumentException("memberRepository is required");
        }
        if (borrowingService == null) {
            throw new IllegalArgumentException("borrowingService is required");
        }
        if (statisticsService == null) {
            throw new IllegalArgumentException("statisticsService is required");
        }

        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.borrowingService = borrowingService;
        this.statisticsService = statisticsService;
    }

    public void registerBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book is required");
        }
        bookRepository.save(book);
    }

    public void registerMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("member is required");
        }
        memberRepository.save(member);
    }

    public void borrowBook(String memberId, String bookId) {
        Member member = getMemberOrThrow(memberId);
        Book book = getBookOrThrow(bookId);

        borrowingService.borrowBook(member, book);
        memberRepository.save(member);
        bookRepository.save(book);
    }

    public void returnBook(String memberId, String bookId) {
        Member member = getMemberOrThrow(memberId);
        Book book = getBookOrThrow(bookId);

        borrowingService.returnBook(member, book);
        memberRepository.save(member);
        bookRepository.save(book);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public List<Member> listMembers() {
        return memberRepository.findAll();
    }

    public List<BookDto> listBookDtos() {
        List<BookDto> bookDtos = new ArrayList<BookDto>();
        for (Book book : bookRepository.findAll()) {
            bookDtos.add(BookDto.from(book));
        }
        return bookDtos;
    }

    public List<MemberDto> listMemberDtos() {
        List<MemberDto> memberDtos = new ArrayList<MemberDto>();
        for (Member member : memberRepository.findAll()) {
            memberDtos.add(MemberDto.from(member));
        }
        return memberDtos;
    }

    public int getTotalBooksCount() {
        return statisticsService.getTotalBooksCount();
    }

    private Book getBookOrThrow(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new DomainException("Book not found: " + bookId));
    }

    private Member getMemberOrThrow(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new DomainException("Member not found: " + memberId));
    }
}

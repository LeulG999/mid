package edu.miu.service;

import edu.miu.repository.BookRepository;

public class LibraryStatisticsService {
    private final BookRepository bookRepository;

    public LibraryStatisticsService(BookRepository bookRepository) {
        if (bookRepository == null) {
            throw new IllegalArgumentException("bookRepository is required");
        }
        this.bookRepository = bookRepository;
    }

    public int getTotalBooksCount() {
        return bookRepository.count();
    }
}

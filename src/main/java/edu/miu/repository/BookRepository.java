package edu.miu.repository;

import edu.miu.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(String bookId);

    List<Book> findAll();

    void save(Book book);

    int count();
}

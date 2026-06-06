package edu.miu.repository;

import edu.miu.model.Book;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> booksById = new LinkedHashMap<String, Book>();

    public Optional<Book> findById(String bookId) {
        return Optional.ofNullable(booksById.get(bookId));
    }

    public List<Book> findAll() {
        return new ArrayList<Book>(booksById.values());
    }

    public void save(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book is required");
        }
        booksById.put(book.getBookId(), book);
    }

    public int count() {
        return booksById.size();
    }
}

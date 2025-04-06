package org.geeksforgeeks.digitallibrary.service;

import org.geeksforgeeks.digitallibrary.model.BookModel;
import org.geeksforgeeks.digitallibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel addBook(BookModel book) {
        return this.bookRepository.save(book);
    }

    public BookModel getBookById(long id) {
        return this.bookRepository.findById(id);
    }
}

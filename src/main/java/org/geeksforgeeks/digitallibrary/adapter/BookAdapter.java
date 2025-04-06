package org.geeksforgeeks.digitallibrary.adapter;

import org.geeksforgeeks.digitallibrary.entities.input.BookInputEntity;
import org.geeksforgeeks.digitallibrary.mappers.input.BookInputMapper;
import org.geeksforgeeks.digitallibrary.model.BookModel;
import org.geeksforgeeks.digitallibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookAdapter {

    private final BookInputMapper bookInputMapper;
    private final BookService bookService;

    @Autowired
    public BookAdapter(
            BookInputMapper bookInputMapper,
            BookService bookService
    ) {
        this.bookInputMapper = bookInputMapper;
        this.bookService = bookService;
    }

    public BookModel save(BookInputEntity inputEntity) {
        return this.bookService.addBook(
                this.bookInputMapper.mapToModel(inputEntity)
        );
    }
}

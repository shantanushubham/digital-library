package org.geeksforgeeks.digitallibrary.adapter;

import org.geeksforgeeks.digitallibrary.commons.CommonAdapter;
import org.geeksforgeeks.digitallibrary.entities.input.BookInputEntity;
import org.geeksforgeeks.digitallibrary.mappers.input.BookInputMapper;
import org.geeksforgeeks.digitallibrary.model.BookModel;
import org.geeksforgeeks.digitallibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookAdapter implements CommonAdapter<BookInputEntity, BookModel> {

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

    @Override
    public BookModel save(BookInputEntity inputEntity) {
        return this.bookService.addBook(
                this.bookInputMapper.mapToModel(inputEntity)
        );
    }

    @Override
    public BookModel getById(long id) {
        return this.bookService.getBookById(id);
    }

    @Override
    public List<BookModel> getAll() {
        return this.bookService.getAllBooks();
    }

    @Override
    public BookModel update(BookInputEntity inputEntity) {
        return this.bookService.updateBook(
                this.bookInputMapper.mapToModel(inputEntity)
        );
    }

    @Override
    public void delete(long id) {
        this.bookService.deleteBook(id);
    }
}

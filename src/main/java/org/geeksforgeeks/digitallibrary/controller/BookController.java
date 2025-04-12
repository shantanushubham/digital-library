package org.geeksforgeeks.digitallibrary.controller;

import jakarta.validation.Valid;
import org.geeksforgeeks.digitallibrary.adapter.BookAdapter;
import org.geeksforgeeks.digitallibrary.commons.CommonAdapter;
import org.geeksforgeeks.digitallibrary.entities.input.BookInputEntity;
import org.geeksforgeeks.digitallibrary.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final CommonAdapter<BookInputEntity, BookModel> adapter;

    @Autowired
    public BookController(CommonAdapter<BookInputEntity, BookModel> adapter) {
        this.adapter = adapter;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookInputEntity book) {
        return new ResponseEntity<>(
                this.adapter.save(book),
                HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookInputEntity book) {
        return new ResponseEntity<>(
                this.adapter.update(book),
                HttpStatus.OK
        );
    }
}

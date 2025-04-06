package org.geeksforgeeks.digitallibrary.controller;

import jakarta.validation.Valid;
import org.geeksforgeeks.digitallibrary.adapter.BookAdapter;
import org.geeksforgeeks.digitallibrary.entities.input.BookInputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookAdapter bookAdapter;

    @Autowired
    public BookController(BookAdapter bookAdapter) {
        this.bookAdapter = bookAdapter;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookInputEntity book) {
        return new ResponseEntity<>(
                this.bookAdapter.save(book),
                HttpStatus.CREATED);
    }
}

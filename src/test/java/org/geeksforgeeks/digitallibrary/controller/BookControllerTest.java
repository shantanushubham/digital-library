package org.geeksforgeeks.digitallibrary.controller;

import org.geeksforgeeks.digitallibrary.adapter.BookAdapter;
import org.geeksforgeeks.digitallibrary.commons.CommonAdapter;
import org.geeksforgeeks.digitallibrary.entities.input.BookInputEntity;
import org.geeksforgeeks.digitallibrary.exceptions.ResourceNotFoundException;
import org.geeksforgeeks.digitallibrary.model.BookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private CommonAdapter<BookInputEntity, BookModel> adapter;

    @InjectMocks
    private BookController bookController;

    static BookInputEntity inputEntity;
    static BookModel bookModel;

    @BeforeAll
    static void setUp() {
        inputEntity = BookInputEntity.builder()
                .id(1L)
                .author("JK Rowling")
                .name("Harry Potter and The Chamber of Secrets")
                .description("Its a fantasy book")
                .publishedDate(Instant.now())
                .build();

        bookModel = BookModel.builder()
                .id(1L)
                .author("Shantanu Shubham")
                .name("Harry Potter and The Chamber of Secrets")
                .description("Its a fantasy book")
                .publishedDate(Instant.now())
                .createdAt(Instant.now())
                .updatedAt(null)
                .build();
    }

    // Idea of UT
    // 1. We expect a certain output for a given input
    // 2. Then we run the function that we want to test with given input
    // 3. We check whether the actual output is the same as expected output.

    @Test
    @DisplayName("Adding New Book - Should Return BookModel")
    void testAddBook() {
        Mockito.when(this.adapter.save(inputEntity)).thenReturn(bookModel);

        ResponseEntity<?> actualResponse = this.bookController.addBook(inputEntity);

        Assertions.assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        Assertions.assertEquals(bookModel, actualResponse.getBody());
    }

    @Test
    @DisplayName("Updating a Book - Should Update Book")
    void testUpdateBook() {
        BookInputEntity modifiedInput = inputEntity.withDescription("Its a wizard fantasy book");
        BookModel modifiedModel = bookModel.withDescription("Its a wizard fantasy book");

        Mockito.when(this.adapter.update(modifiedInput)).thenReturn(modifiedModel);

        ResponseEntity<?> response = this.bookController.updateBook(modifiedInput);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(modifiedModel, response.getBody());
    }

    @Test
    @DisplayName("Updating a Book - Should throw BAD_REQUEST")
    void testUpdateBook_updateFailed() {
        BookInputEntity modifiedInput = inputEntity.withDescription("Its a wizard fantasy book").withId(2);

        Mockito.when(this.adapter.update(modifiedInput)).thenThrow(
                new ResourceNotFoundException(Mockito.any()));

        ResponseEntity<?> response = this.bookController.updateBook(modifiedInput);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}

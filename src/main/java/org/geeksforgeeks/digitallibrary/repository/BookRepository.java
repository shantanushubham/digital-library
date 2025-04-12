package org.geeksforgeeks.digitallibrary.repository;

import org.geeksforgeeks.digitallibrary.entities.output.BookOutputEntity;
import org.geeksforgeeks.digitallibrary.exceptions.ResourceNotFoundException;
import org.geeksforgeeks.digitallibrary.mappers.output.BookOutputMapper;
import org.geeksforgeeks.digitallibrary.model.BookModel;
import org.geeksforgeeks.digitallibrary.repository.jpa.BookJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class BookRepository {

    private final BookJPARepository bookJPARepository;
    private final BookOutputMapper bookOutputMapper;


    @Autowired
    public BookRepository(BookJPARepository bookJPARepository, BookOutputMapper bookOutputMapper) {
        this.bookJPARepository = bookJPARepository;
        this.bookOutputMapper = bookOutputMapper;
    }

    public BookModel save(BookModel book) {
        // Convert model to output-entity
        BookOutputEntity outputEntity = this.bookOutputMapper.mapFromModel(book);
        // save in DB
        BookOutputEntity savedOutputEntity = this.bookJPARepository.save(outputEntity);
        // convert the saved output entity into model
        // return the model
        return this.bookOutputMapper.mapToModel(savedOutputEntity);
    }

    public BookModel findById(long id) {
        Optional<BookOutputEntity> optionalBookOutputEntity = this.bookJPARepository.findById(id);
        return optionalBookOutputEntity.map(this.bookOutputMapper::mapToModel).orElseThrow(() ->
                new ResourceNotFoundException(BookModel.class, "id", String.valueOf(id)));
    }

    public BookModel update(BookModel bookModel) {
        BookModel existingModel = this.findById(bookModel.getId());
        bookModel.setUpdatedAt(Instant.now());
        bookModel.setCreatedAt(existingModel.getCreatedAt());
        return this.save(bookModel);
    }

}

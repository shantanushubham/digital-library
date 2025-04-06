package org.geeksforgeeks.digitallibrary.mappers.input;

import org.geeksforgeeks.digitallibrary.entities.input.BookInputEntity;
import org.geeksforgeeks.digitallibrary.model.BookModel;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class BookInputMapper {

    public BookModel mapToModel(BookInputEntity inputEntity) {
        return BookModel.builder()
                .id(inputEntity.getId())
                .name(inputEntity.getName())
                .author(inputEntity.getAuthor())
                .description(inputEntity.getDescription())
                .publishedDate(inputEntity.getPublishedDate())
                .createdAt(Instant.now())
                .build();
    }
}

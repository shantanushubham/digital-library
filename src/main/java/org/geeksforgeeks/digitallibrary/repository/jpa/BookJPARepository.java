package org.geeksforgeeks.digitallibrary.repository.jpa;

import org.geeksforgeeks.digitallibrary.entities.output.BookOutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJPARepository extends JpaRepository<BookOutputEntity, Long> {
}

package org.geeksforgeeks.digitallibrary.repository.jpa;

import org.geeksforgeeks.digitallibrary.entities.output.UserOutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJPARepository extends JpaRepository<UserOutputEntity, Long> {

    Optional<UserOutputEntity> findByEmail(String email);

}

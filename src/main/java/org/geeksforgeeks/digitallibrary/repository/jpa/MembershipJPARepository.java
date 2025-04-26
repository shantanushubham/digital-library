package org.geeksforgeeks.digitallibrary.repository.jpa;

import org.geeksforgeeks.digitallibrary.entities.output.MembershipOutputEntity;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipJPARepository extends JpaRepository<MembershipOutputEntity, Long> {

    /**
     * Finds if the user with ID has any membership that is not of the status given as the argument.
     *
     * @param id     The User ID
     * @param status The membership status that the user does not have
     * @return Optional of MembershipOutputEntity
     */
    Optional<MembershipOutputEntity> findByUser_IdAndStatusNot(long id, MembershipStatus status);
}

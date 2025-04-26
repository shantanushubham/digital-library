package org.geeksforgeeks.digitallibrary.repository;

import lombok.extern.slf4j.Slf4j;
import org.geeksforgeeks.digitallibrary.entities.output.MembershipOutputEntity;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;
import org.geeksforgeeks.digitallibrary.exceptions.ResourceNotFoundException;
import org.geeksforgeeks.digitallibrary.mappers.output.MembershipOutputMapper;
import org.geeksforgeeks.digitallibrary.model.MembershipModel;
import org.geeksforgeeks.digitallibrary.repository.jpa.MembershipJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class MembershipRepository {

    private final MembershipJPARepository membershipJPARepository;
    private final MembershipOutputMapper membershipOutputMapper;

    @Autowired
    public MembershipRepository(MembershipJPARepository membershipJPARepository, MembershipOutputMapper membershipOutputMapper) {
        this.membershipJPARepository = membershipJPARepository;
        this.membershipOutputMapper = membershipOutputMapper;
    }

    public MembershipModel addMembership(MembershipModel membershipModel) {
        MembershipOutputEntity outputEntity = this.membershipOutputMapper.mapFromModel(membershipModel);
        outputEntity = this.membershipJPARepository.save(outputEntity);
        log.info("Saved new membership with ID: {} for user with ID: {}",
                outputEntity.getId(), outputEntity.getUser().getId());
        return this.membershipOutputMapper.mapToModel(outputEntity);
    }

    public MembershipModel getMembershipById(long id) {
        return this.membershipJPARepository.findById(id)
                .map((o) -> {
                    log.info("Membership with ID: {} was successfully found.", id);
                    return this.membershipOutputMapper.mapToModel(o);
                })
                .orElseThrow(() -> new ResourceNotFoundException(MembershipModel.class, "id", String.valueOf(id)));
    }

    public MembershipModel updateMembershipStatus(long membershipId, MembershipStatus status) {
        MembershipOutputEntity outputEntity =
                this.membershipJPARepository.findById(membershipId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        MembershipOutputEntity.class,
                                        "id",
                                        String.valueOf(membershipId)));
        outputEntity.setStatus(status);
        outputEntity = this.membershipJPARepository.save(outputEntity);
        log.info("Membership with ID: {} was successfully updated.", membershipId);
        return this.membershipOutputMapper.mapToModel(outputEntity);
    }

    public Optional<MembershipOutputEntity> getNonExpiredMembershipForUser(long userId) {
        return this.membershipJPARepository.findByUser_IdAndStatusNot(userId, MembershipStatus.EXPIRED);
    }

}

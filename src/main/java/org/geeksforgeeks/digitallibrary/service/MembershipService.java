package org.geeksforgeeks.digitallibrary.service;

import lombok.extern.slf4j.Slf4j;
import org.geeksforgeeks.digitallibrary.entities.output.MembershipOutputEntity;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;
import org.geeksforgeeks.digitallibrary.exceptions.MembershipException;
import org.geeksforgeeks.digitallibrary.model.MembershipModel;
import org.geeksforgeeks.digitallibrary.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MembershipService {

    private final MembershipRepository membershipRepository;

    @Autowired
    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public MembershipModel addMembership(MembershipModel membershipModel) {
        if (this.doesUserHaveActiveMembership(membershipModel.getUserId())) {
            throw new MembershipException(membershipModel.getUserId(), true);
        }
        membershipModel = this.membershipRepository.addMembership(membershipModel);
        return membershipModel;
    }

    public MembershipModel updateMembershipStatus(long userId, MembershipStatus status) {
        Optional<MembershipOutputEntity> optional = this.membershipRepository.getNonExpiredMembershipForUser(userId);
        if (optional.isEmpty()) {
            throw new MembershipException(userId, false);
        }
        return this.membershipRepository.updateMembershipStatus(optional.get().getId(), status);
    }

    private boolean doesUserHaveActiveMembership(long userId) {
        Optional<MembershipOutputEntity> optional = this.membershipRepository.getNonExpiredMembershipForUser(userId);
        if (optional.isPresent()) {
            log.error("The user with ID: {} already has an active membership with ID: {}", userId, optional.get().getId());
            return true;
        }
        log.info("The user with ID: {} does not have an active membership.", userId);
        return false;
    }
}

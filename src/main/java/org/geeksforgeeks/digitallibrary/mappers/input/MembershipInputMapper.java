package org.geeksforgeeks.digitallibrary.mappers.input;

import org.geeksforgeeks.digitallibrary.entities.input.MembershipInputEntity;
import org.geeksforgeeks.digitallibrary.enums.MembershipPlan;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;
import org.geeksforgeeks.digitallibrary.model.MembershipModel;
import org.geeksforgeeks.digitallibrary.model.UserModel;
import org.geeksforgeeks.digitallibrary.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class MembershipInputMapper {

    private final UserRepository userRepository;

    public MembershipInputMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MembershipModel mapToModel(MembershipInputEntity membershipInputEntity) {
        UserModel userModel = this.userRepository.getUserById(membershipInputEntity.getUserId());
        Instant[] startEnd = this.getStartAndEndDate(membershipInputEntity);
        return MembershipModel.builder()
                .user(userModel)
                .startDate(startEnd[0])
                .endDate(startEnd[1])
                .status(MembershipStatus.ACTIVE)
                .plan(membershipInputEntity.getPlan())
                .build();
    }

    private Instant[] getStartAndEndDate(MembershipInputEntity membershipInputEntity) {
        MembershipPlan plan = membershipInputEntity.getPlan();
        Instant start = Instant.now();
        Instant end = null;
        switch (plan) {
            case THREE_MONTHS -> end = this.addMonthsToInstant(start, 3);
            case SIX_MONTHS -> end = this.addMonthsToInstant(start, 6);
            case ONE_YEAR -> end = this.addMonthsToInstant(start, 12);
        }
        return new Instant[]{start, end};
    }

    private Instant addMonthsToInstant(Instant instant, int monthsToAdd) {
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        ZonedDateTime updated = zonedDateTime.plusMonths(monthsToAdd);
        return updated.toInstant();
    }
}

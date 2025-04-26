package org.geeksforgeeks.digitallibrary.mappers.output;

import org.geeksforgeeks.digitallibrary.entities.output.MembershipOutputEntity;
import org.geeksforgeeks.digitallibrary.entities.output.UserOutputEntity;
import org.geeksforgeeks.digitallibrary.model.MembershipModel;
import org.geeksforgeeks.digitallibrary.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MembershipOutputMapper {

    private final UserOutputMapper userOutputMapper;

    @Autowired
    public MembershipOutputMapper(UserOutputMapper userOutputMapper) {
        this.userOutputMapper = userOutputMapper;
    }

    public MembershipModel mapToModel(MembershipOutputEntity outputEntity) {
        UserModel userModel = this.userOutputMapper.mapToModel(outputEntity.getUser());
        return MembershipModel.builder()
                .id(outputEntity.getId())
                .user(userModel)
                .startDate(outputEntity.getStartDate())
                .endDate(outputEntity.getEndDate())
                .status(outputEntity.getStatus())
                .plan(outputEntity.getPlan())
                .build();
    }

    public MembershipOutputEntity mapFromModel(MembershipModel model) {
        UserOutputEntity user = this.userOutputMapper.mapFromModel(model.getUser());
        return MembershipOutputEntity.builder()
                .id(model.getId())
                .user(user)
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .status(model.getStatus())
                .plan(model.getPlan())
                .build();
    }


}

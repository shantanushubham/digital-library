package org.geeksforgeeks.digitallibrary.adapter;

import org.geeksforgeeks.digitallibrary.commons.CommonAdapter;
import org.geeksforgeeks.digitallibrary.entities.input.MembershipInputEntity;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;
import org.geeksforgeeks.digitallibrary.mappers.input.MembershipInputMapper;
import org.geeksforgeeks.digitallibrary.model.MembershipModel;
import org.geeksforgeeks.digitallibrary.service.MembershipService;
import org.geeksforgeeks.digitallibrary.utils.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MembershipAdapter implements CommonAdapter<MembershipInputEntity, MembershipModel> {

    private final MembershipService membershipService;
    private final MembershipInputMapper membershipInputMapper;

    @Autowired
    public MembershipAdapter(MembershipService membershipService, MembershipInputMapper membershipInputMapper) {
        this.membershipService = membershipService;
        this.membershipInputMapper = membershipInputMapper;
    }

    @Override
    public MembershipModel save(MembershipInputEntity membershipInputEntity) {
        return this.membershipService
                .addMembership(this.membershipInputMapper.mapToModel(membershipInputEntity));
    }

    @Override
    public MembershipModel getById(long id) {
        return null;
    }

    @Override
    public List<MembershipModel> getAll() {
        return List.of();
    }

    @Override
    public MembershipModel update(MembershipInputEntity membershipInputEntity) {
        return Todo.todo();
    }

    @Override
    public void delete(long id) {
        Todo.todo();
    }

    public MembershipModel changeMembershipStatus(long membershipId, MembershipStatus status) {
        return this.membershipService.updateMembershipStatus(membershipId, status);
    }
}

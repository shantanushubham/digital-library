package org.geeksforgeeks.digitallibrary.adapter;

import org.geeksforgeeks.digitallibrary.commons.CommonAdapter;
import org.geeksforgeeks.digitallibrary.entities.input.UserInputEntity;
import org.geeksforgeeks.digitallibrary.mappers.input.UserInputMapper;
import org.geeksforgeeks.digitallibrary.model.UserModel;
import org.geeksforgeeks.digitallibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAdapter implements CommonAdapter<UserInputEntity, UserModel> {

    private final UserInputMapper userInputMapper;
    private final UserService userService;

    @Autowired
    public UserAdapter(UserInputMapper userInputMapper, UserService userService) {
        this.userInputMapper = userInputMapper;
        this.userService = userService;
    }

    @Override
    public UserModel save(UserInputEntity input) {
        return this.userService.addUser(this.userInputMapper.mapToModel(input));
    }

    @Override
    public UserModel getById(long id) {
        return null;
    }

    @Override
    public List<UserModel> getAll() {
        return List.of();
    }

    @Override
    public UserModel update(UserInputEntity input) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}

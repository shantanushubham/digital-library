package org.geeksforgeeks.digitallibrary.repository;

import org.geeksforgeeks.digitallibrary.entities.output.UserOutputEntity;
import org.geeksforgeeks.digitallibrary.exceptions.ResourceNotFoundException;
import org.geeksforgeeks.digitallibrary.mappers.output.UserOutputMapper;
import org.geeksforgeeks.digitallibrary.model.UserModel;
import org.geeksforgeeks.digitallibrary.repository.jpa.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    private final UserJPARepository userJPARepository;
    private final UserOutputMapper userOutputMapper;

    @Autowired
    public UserRepository(UserJPARepository userJPARepository, UserOutputMapper userOutputMapper) {
        this.userJPARepository = userJPARepository;
        this.userOutputMapper = userOutputMapper;
    }

    public UserModel addUser(UserModel userModel) {
        UserOutputEntity outputEntity = this.userOutputMapper.mapFromModel(userModel);
        UserOutputEntity savedOutputEntity = this.userJPARepository.save(outputEntity);
        return this.userOutputMapper.mapToModel(savedOutputEntity);
    }

    public UserModel getUserByEmail(String email) {
        return this.userJPARepository.findByEmail(email).map(this.userOutputMapper::mapToModel)
                .orElseThrow(() -> new ResourceNotFoundException(UserModel.class, "email", email));
    }
}

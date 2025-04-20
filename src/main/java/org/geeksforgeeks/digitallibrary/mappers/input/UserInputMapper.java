package org.geeksforgeeks.digitallibrary.mappers.input;

import org.geeksforgeeks.digitallibrary.entities.input.UserInputEntity;
import org.geeksforgeeks.digitallibrary.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserInputMapper {

    public UserModel mapToModel(UserInputEntity input) {
        return UserModel.builder()
                .email(input.getEmail())
                .password(input.getPassword())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .role(input.getRole())
                .dob(input.getDateOfBirth().toInstant())
                .build();
    }

}

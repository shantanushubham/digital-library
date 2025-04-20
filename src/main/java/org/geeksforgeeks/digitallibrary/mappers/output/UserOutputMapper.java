package org.geeksforgeeks.digitallibrary.mappers.output;

import org.geeksforgeeks.digitallibrary.entities.output.UserOutputEntity;
import org.geeksforgeeks.digitallibrary.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserOutputMapper {

    public UserModel mapToModel(UserOutputEntity output) {
        return UserModel.builder()
                .id(output.getId())
                .firstName(output.getFirstName())
                .lastName(output.getLastName())
                .email(output.getEmail())
                .password(output.getPassword())
                .dob(output.getDob())
                .phoneNumber(output.getPhoneNumber())
                .role(output.getRole())
                .build();
    }

    public UserOutputEntity mapFromModel(UserModel userModel) {
        return UserOutputEntity.builder()
                .id(userModel.getId())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .dob(userModel.getDob())
                .phoneNumber(userModel.getPhoneNumber())
                .role(userModel.getRole())
                .build();
    }
}

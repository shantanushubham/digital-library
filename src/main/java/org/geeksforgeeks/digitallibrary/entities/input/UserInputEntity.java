package org.geeksforgeeks.digitallibrary.entities.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.geeksforgeeks.digitallibrary.enums.UserRole;

import java.util.Date;

@Data
@Builder
@With
public class UserInputEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @Size(min = 5)
    private String password;

    private Date dateOfBirth;

    @Builder.Default
    private UserRole role = UserRole.USER;
}

package org.geeksforgeeks.digitallibrary.entities.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.geeksforgeeks.digitallibrary.enums.MembershipPlan;

@Data
public class MembershipInputEntity {

    @NotNull
    private long userId;

    @NotNull
    private MembershipPlan plan;

}

package org.geeksforgeeks.digitallibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.geeksforgeeks.digitallibrary.enums.MembershipPlan;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;

import java.time.Instant;

@Data
@Builder
@With
public class MembershipModel {

    private long id;

    @JsonIgnore
    private UserModel user;

    private Instant startDate;
    private Instant endDate;
    private MembershipStatus status;
    private MembershipPlan plan;

    @JsonProperty
    public long getUserId() {
        return this.getUser().getId();
    }
}

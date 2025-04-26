package org.geeksforgeeks.digitallibrary.entities.output;

import jakarta.persistence.*;
import lombok.*;
import org.geeksforgeeks.digitallibrary.enums.MembershipPlan;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;

import java.time.Instant;

@Entity
@Table(name = "membership")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class MembershipOutputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserOutputEntity user;

    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Column(name = "status", nullable = false)
    private MembershipStatus status;

    @Column(name = "plan", nullable = false)
//    @Enumerated(EnumType.ORDINAL)
    private MembershipPlan plan;

}

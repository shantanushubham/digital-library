package org.geeksforgeeks.digitallibrary.entities.output;

import jakarta.persistence.*;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;

import java.time.Instant;

@Entity
@Table(name = "membership")
public class MembershipOutputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserOutputEntity user;

    private Instant startDate;
    private Instant endDate;
    private MembershipStatus status;

}

// Rahul | Shantanu | Nikhil
 // 1
// Rahul -><- Shantanu
// Shantanu -> Nikhil
// { Rahul -> Shantanu -> Rahul -> Shantanu -> Rahul }

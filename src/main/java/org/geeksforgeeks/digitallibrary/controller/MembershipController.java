package org.geeksforgeeks.digitallibrary.controller;

import jakarta.validation.Valid;
import org.geeksforgeeks.digitallibrary.adapter.MembershipAdapter;
import org.geeksforgeeks.digitallibrary.entities.input.MembershipInputEntity;
import org.geeksforgeeks.digitallibrary.enums.MembershipStatus;
import org.geeksforgeeks.digitallibrary.exceptions.MembershipException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipAdapter membershipAdapter;

    @Autowired
    public MembershipController(MembershipAdapter membershipAdapter) {
        this.membershipAdapter = membershipAdapter;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMembership(@Valid @RequestBody MembershipInputEntity membershipInputEntity) {
        try {
            return new ResponseEntity<>(this.membershipAdapter.save(membershipInputEntity), HttpStatus.CREATED);
        } catch (MembershipException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<?> changeMembershipStatus(@PathVariable long userId, @RequestParam MembershipStatus status) {
        if (status == MembershipStatus.EXPIRED) {
            return new ResponseEntity<>("User cannot change the membership status to EXPIRED.", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(this.membershipAdapter.changeMembershipStatus(userId, status), HttpStatus.OK);
        } catch (MembershipException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

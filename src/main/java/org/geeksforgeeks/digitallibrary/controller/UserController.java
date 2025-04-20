package org.geeksforgeeks.digitallibrary.controller;

import jakarta.validation.Valid;
import org.geeksforgeeks.digitallibrary.commons.CommonAdapter;
import org.geeksforgeeks.digitallibrary.entities.input.UserInputEntity;
import org.geeksforgeeks.digitallibrary.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CommonAdapter<UserInputEntity, UserModel> adapter;

    @Autowired
    public UserController(CommonAdapter<UserInputEntity, UserModel> adapter) {
        this.adapter = adapter;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserInputEntity userInputEntity) {
        return new ResponseEntity<>(this.adapter.save(userInputEntity), HttpStatus.CREATED);
    }
}

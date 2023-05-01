package com.workshop.testapp.rest;

import com.workshop.testapp.model.User;
import com.workshop.testapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/users", produces = "application/json")
public class UserResource {

    // GET /api/users
    // GET /api/users/{id}
    // POST /api/users
    // PUT /api/users/{id}
    // DELETE /api/users/{id}

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createUser(User user) {
        return new ResponseEntity<>(userService.createUser(user).getId(), HttpStatus.CREATED);
    }
}

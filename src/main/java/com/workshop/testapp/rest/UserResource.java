package com.workshop.testapp.rest;

import com.workshop.testapp.domain.User;
import com.workshop.testapp.model.UserDTO;
import com.workshop.testapp.services.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
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
    @ApiResponse(responseCode = "200", description = "Return user by id")
    public ResponseEntity<User> getUserById(Long id) {
        var response = userService.getUserById(id);
        return response == null? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "User created")
    public ResponseEntity<Long> createUser(UserDTO user) {
        return new ResponseEntity<>(userService.createUser(user).getId(), HttpStatus.CREATED);
    }
}

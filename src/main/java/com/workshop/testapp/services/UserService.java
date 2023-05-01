package com.workshop.testapp.services;

import com.workshop.testapp.model.User;
import com.workshop.testapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    //constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //create user
    public User createUser(User user) {
       return userRepository.save(user);
    }

    //get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //update user by id
    public void updateUserById(Long id) {
        userRepository.findById(id);
    }

    //get user by id
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    //get user by phone
    public void getUserByPhone(String phone) {
        userRepository.findByPhone(phone);
    }

    //get user by email
    public void getUserByEmail(String email) {
        userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {

        //find user by id
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}

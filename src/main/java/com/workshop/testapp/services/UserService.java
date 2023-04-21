package com.workshop.testapp.services;

import com.workshop.testapp.repositories.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    //constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //create user
    public void createUser(String phone) {
        userRepository.findByPhone(phone);
    }

    //get all users
    public void getAllUsers() {
        userRepository.findAll();
    }

    //update user by id
    public void updateUserById(Long id) {
        userRepository.findById(id);
    }

    //get user by id
    public void getUserById(Long id) {
        userRepository.findById(id);
    }

    //get user by phone
    public void getUserByPhone(String phone) {
        userRepository.findByPhone(phone);
    }

    //get user by email
    public void getUserByEmail(String email) {
        userRepository.findByEmail(email);
    }




}

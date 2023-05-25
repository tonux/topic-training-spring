package com.workshop.testapp.services;

import com.workshop.testapp.domain.User;
import com.workshop.testapp.model.UserDTO;
import com.workshop.testapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public User createUser(UserDTO user) {
       return userRepository.save(mapToEntity(user));
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

    private UserDTO mapToDTO(final User user){
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    private User mapToEntity(final UserDTO userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());

        return user;
    }
}

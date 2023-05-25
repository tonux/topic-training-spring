package com.workshop.testapp.services;

import com.workshop.testapp.domain.User;
import com.workshop.testapp.model.UserDTO;
import com.workshop.testapp.repositories.UserRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    private User user1;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        user1 = new User("Medoune", "770000000", "medoune@gmail.com");
        user1.setId(1l);
    }

    @Test
    void createUser() {
        UserDTO user = new UserDTO("Medoune", "770000000", "medoune@gmail.com");

        when(userRepository.save(any())).thenReturn(user1); // I mock the save method of the repository

        User userResponse = userService.createUser(user);

        assertNotNull(userResponse);

        assertEquals(userResponse.getName(), user.getName());
        assertEquals(userResponse.getPhone(), user.getPhone());
        assertEquals(userResponse.getEmail(), user.getEmail());
        assertTrue(userResponse.getId() > 0);

        verify(userRepository, atLeastOnce()).save(any());
    }

    @Test
    void getAllUsers() {

        when(userRepository.findAll()).thenReturn(java.util.List.of(user1));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getName(), user1.getName());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void updateUserById() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByPhone() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}
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
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user1));
        when(userRepository.save(any())).thenReturn(user1);

        UserDTO user = new UserDTO("Medoune", "770000000", "medoune@gmail.com");

        User userResponse = userService.updateUserById(1l, user);

        assertNotNull(userResponse);
        assertEquals(userResponse.getName(), user.getName());
        assertEquals(userResponse.getPhone(), user.getPhone());
        assertEquals(userResponse.getEmail(), user.getEmail());
        assertEquals(userResponse.getId(), user1.getId());

    }

    @Test
    void getUserById() {
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user1));

        User userResponse = userService.getUserById(1l);

        assertNotNull(userResponse);
        assertEquals(userResponse.getName(), user1.getName());
        assertEquals(userResponse.getPhone(), user1.getPhone());
        assertEquals(userResponse.getEmail(), user1.getEmail());
        assertEquals(userResponse.getId(), user1.getId());

    }

    @Test
    void getUserByPhone() {
        when(userRepository.findByPhone(any())).thenReturn(user1);

        User userResponse = userService.getUserByPhone("770000000");

        assertNotNull(userResponse);
        assertEquals(userResponse.getName(), user1.getName());
        assertEquals(userResponse.getPhone(), user1.getPhone());
        assertEquals(userResponse.getEmail(), user1.getEmail());
        assertEquals(userResponse.getId(), user1.getId());

    }

    @Test
    void getUserByEmail() {
        when(userRepository.findByEmail(any())).thenReturn(user1);

        User userResponse = userService.getUserByEmail("medoune@gmail.com");

        assertNotNull(userResponse);
        assertEquals(userResponse.getName(), user1.getName());
        assertEquals(userResponse.getPhone(), user1.getPhone());
        assertEquals(userResponse.getEmail(), user1.getEmail());
        assertEquals(userResponse.getId(), user1.getId());

    }

    @Test
    void deleteUser() {
        UserDTO user = new UserDTO("Medoune", "770000000", "medoune@gmail.com");
        user.setId(1l);

        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user1));
        doNothing().when(userRepository).delete(any());

        userService.deleteUser(user.getId());

        verify(userRepository, times(1)).delete(any());


    }

    @Test
    void updateUser() {
        when(userRepository.findById(any())).thenReturn(java.util.Optional.of(user1));
        when(userRepository.save(any())).thenReturn(user1);

        User user = new User("Medoune", "770000000", "medoune@gmail.com");
        User user2 = userService.getUserById(user1.getId());
        user2.copy(user);


        User userResponse = userService.updateUser(user2);

        assertNotNull(userResponse);
        assertEquals(userResponse.getName(), user.getName());
        assertEquals(userResponse.getPhone(), user.getPhone());
        assertEquals(userResponse.getEmail(), user.getEmail());
        assertEquals(userResponse.getId(), user1.getId());

    }
}
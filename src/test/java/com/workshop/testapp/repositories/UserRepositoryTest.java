package com.workshop.testapp.repositories;

import com.workshop.testapp.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createUser(){
        user = userRepository.save(new User("Diop", randomPhone(), "amadou@gmail.com" ));
        assertNotNull(user);
        assertTrue(user.getId() > 0);
    }

    @Test
    void findByPhone() {
        //Given
        userRepository.save(new User("Ba", "1234566565", "aissatou@gmail.com" ));
        // When
        User user = userRepository.findByPhone("1234566565");

        // Then
        assertNotNull(user);
        assertEquals("Ba", user.getName());
        assertEquals("aissatou@gmail.com", user.getEmail());

    }

    @Test
    void findByEmail() {
        //TODO : implement this test
        //Given
        userRepository.save(new User("Fall", "5646513215", "modou@gmail.com" ));
        // When
        User user = userRepository.findByEmail("modou@gmail.com");

        // Then
        assertNotNull(user);
        assertEquals("Fall", user.getName());
        assertEquals("5646513215", user.getPhone());


    }

    @Test
    void findByName() {

        //TODO : implement this test
        //Given
        userRepository.save(new User("Diop", "12345678", "amadou@gmail.com" ));
        // When
        User user = userRepository.findByName("Diop");

        // Then
        assertNotNull(user);
        assertEquals("12345678", user.getPhone());
        assertEquals("amadou@gmail.com", user.getEmail());

    }

    private String randomPhone(){
        return "77" + Math.random() * 10000000;
    }
}
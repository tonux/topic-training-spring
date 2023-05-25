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
        userRepository.save(new User("Diop", "12345678", "amadou@gmail.com" ));
        // When
        User user = userRepository.findByPhone("12345678");

        // Then
        assertNotNull(user);
        assertEquals("Diop", user.getName());
        assertEquals("amadou@gmail.com", user.getEmail());

    }

    // @Test
    void findByEmail() {
        //TODO : implement this test
    }

    //@Test
    void findByUsername() {

        //TODO : implement this test
    }

    private String randomPhone(){
        return "77" + Math.random() * 10000000;
    }
}
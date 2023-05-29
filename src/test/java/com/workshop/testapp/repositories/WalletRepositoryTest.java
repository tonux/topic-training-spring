package com.workshop.testapp.repositories;

import com.workshop.testapp.domain.User;
import com.workshop.testapp.domain.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalletRepositoryTest {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;

    private Wallet wallet;
    private Wallet wallet1;
    private User userResponse;

    @BeforeEach
    void setUp() {

        //Given
        userRepository.save(new User("Fall","7746545465", "modou@gmail.com" ));
        userResponse = userRepository.findByPhone("7746545465");

    }

    @Test
    void findAllByUserId() {

        //When

        wallet = new Wallet("Orange Money", 1000);
        wallet.setUser(userResponse);
        walletRepository.save(wallet);

        wallet1 = new Wallet("Wave", 1000);
        wallet1.setUser(userResponse);
        walletRepository.save(wallet1);

        //Then
        assertEquals(2, walletRepository.findAllByUserId(userResponse.getId()).size());
    }
}
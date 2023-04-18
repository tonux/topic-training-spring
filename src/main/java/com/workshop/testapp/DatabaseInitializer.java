package com.workshop.testapp;

import com.workshop.testapp.model.User;
import com.workshop.testapp.repositories.TransactionRepository;
import com.workshop.testapp.repositories.UserRepository;
import com.workshop.testapp.repositories.WalletRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DatabaseInitializer {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public DatabaseInitializer(UserRepository userRepository, WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostConstruct
    public void init() {
        //create a user
        User user = new User("770000000", "Doe@gmail.com");
        userRepository.save(user);

        User userResponse = userRepository.findByPhone("770000000");

        System.out.println(userResponse.getId());


    }
}

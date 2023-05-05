package com.workshop.testapp;

import com.workshop.testapp.domain.Transaction;
import com.workshop.testapp.domain.User;
import com.workshop.testapp.domain.Wallet;
import com.workshop.testapp.repositories.TransactionRepository;
import com.workshop.testapp.repositories.UserRepository;
import com.workshop.testapp.repositories.WalletRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

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
        User user = new User("Médoune Siby Georges Baldé","771331206", "medounesibygeorgesbald2@gmail.com");
        userRepository.save(user);
        User userResponse = userRepository.findByPhone("771331206");

        //create a wallet
        Wallet wallet = new Wallet("Orange Money", 1000);
        wallet.setUser(userResponse);
        walletRepository.save(wallet);
        /*
        User user1 = new User("7777777777", "test@gmail.com");
        userRepository.save(user1);
        User userResponse1 = userRepository.findByPhone("7777777777");
        */
        Wallet wallet1 = new Wallet("Wave", 5000);
        wallet1.setUser(userResponse);
        walletRepository.save(wallet1);

        User user2 = new User("test","7777777777","test@gmail.com");
        userRepository.save(user2);
        User userResponse2 = userRepository.findByPhone("7777777777");

        Wallet wallet2 = new Wallet("Free Money", 1000);
        wallet2.setUser(userResponse2);
        walletRepository.save(wallet2);


        //create a transaction
        Transaction transaction = new Transaction( "Achat de crédit", 1000.0, java.time.LocalDate.now());
        transaction.setWallet(wallet);
        transactionRepository.save(transaction);








        System.out.println(userResponse.getId());


    }
}

package com.workshop.testapp.repositories;

import com.workshop.testapp.domain.Transaction;
import com.workshop.testapp.domain.User;
import com.workshop.testapp.domain.Wallet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    UserRepository userRepository;

    private User userResponse;
    private Wallet wallet;
    private Wallet walletResponse;

    Transaction transaction;

    @BeforeEach
    void setUp() {

        userRepository.save(new User("Fall","7746545465", "modou@gmail.com" ));
        userResponse = userRepository.findByPhone("7746545465");

        wallet = new Wallet("Orange Money", 1000);
        wallet.setUser(userResponse);
        walletRepository.save(wallet);
        walletResponse = walletRepository.findById(1);

        transaction = new Transaction( "Achat", 1000.0, java.time.LocalDate.now());
        transaction.setWallet(walletResponse);
        transactionRepository.save(transaction);

    }



    @Test
    void findAllByWalletId() {
        //When
        List<Transaction> transactions = transactionRepository.findAllByWalletId(2L);

        //Then
        assertNotNull(transactions);
        //l'initit [DatabaseInitializer] a créé une transaction
        assertEquals(1, transactions.size());
        assertEquals("Achat", transactions.get(0).getDescription());
        assertEquals(1000.0, transactions.get(0).getAmount());
        assertNotNull(transactions.get(0).getDate());

        assertEquals(walletResponse.getId(), transactions.get(0).getWallet().getId());

    }

}
package com.workshop.testapp.services;

import com.workshop.testapp.model.Wallet;
import com.workshop.testapp.repositories.UserRepository;
import com.workshop.testapp.repositories.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private UserRepository userRepository;
    private final WalletRepository walletRepository;

    public WalletService(UserRepository userRepository,
                         WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    // create wallet

    public void createWallet(Wallet wallet){
        //verify if wallet exists by phone number
        Wallet walletResponse = walletRepository.findByUserByPhone("770000000");
        if(walletResponse != null){
            throw new RuntimeException("Wallet already exists");
        }
        walletRepository.save(wallet);

        //call send notification service
    }

    //TODO:  get all wallets

    //TODO: get wallet by id

    //TODO: update wallet

    //TODO: delete wallet
}

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
/*
    public void createWallet(Wallet wallet){
        //verify if wallet exists by phone number
        Wallet walletResponse = walletRepository.findByUserByPhone("770000000");
        if(walletResponse != null){
            throw new RuntimeException("Wallet already exists");
        }
        walletRepository.save(wallet);

        //call send notification service
    }
*/
    //Get all wallets
    public void getAllWallets(){
        walletRepository.findAll();
    }


    //Get wallet by id
    public void getWalletById(Long id){
        walletRepository.findById(id);
    }

    //Update wallet
    public void updateWallet(Wallet wallet){
        walletRepository.save(wallet);
    }

    //Delete wallet
    public void deleteWallet(Long id){
        walletRepository.deleteById(id);
    }
}

package com.workshop.testapp.services;

import com.workshop.testapp.domain.Wallet;
import com.workshop.testapp.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // create wallet
    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    //Get all wallets
    public List<Wallet> getAllWallets(){
        return walletRepository.findAll();
    }


    //Get wallet by id
    public Wallet getWalletById(Long id){
        Wallet wallet = walletRepository.findById(id).orElse(null);
        return wallet;

    }

    //Update wallet
    public void updateWallet(Wallet wallet){
        walletRepository.save(wallet);
    }

    //Delete wallet
    public void deleteWallet(Long id){
        walletRepository.deleteById(id);
    }



    public List<Wallet> getUserWalletsByUserId(Long id) {
        List walletList = walletRepository.findAllByUserId(id);
        return walletList;
    }


}

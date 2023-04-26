package com.workshop.testapp.controller;

import com.workshop.testapp.model.User;
import com.workshop.testapp.model.Wallet;
import com.workshop.testapp.repositories.UserRepository;
import com.workshop.testapp.services.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WalletsController {

    private WalletService walletService;
    private UserRepository userRepository;

    public WalletsController(WalletService walletService, UserRepository userRepository) {
        this.walletService = walletService;
        this.userRepository = userRepository;
    }

    //Get user wallets by id
    @GetMapping("/wallets/user/{id}")
    public String getUserWalletsByUserId(@PathVariable("id") Long id, Model model){
        List<Wallet> walletList = walletService.getUserWalletsByUserId(id);
        model.addAttribute("wallets", walletList);
        model.addAttribute("userId", id);
        return "wallet-list";
    }

    //Add new wallet

    @GetMapping("/newWalletForm/{userId}")
    public String form(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("wallet", new Wallet());
        model.addAttribute("action", "/addWallet/" +userId);
        return "wallet-form";
    }

    @PostMapping("/addWallet/{userId}")
    public String add(@PathVariable("userId") Long userId,Wallet wallet) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        wallet.setUser(user);
        walletService.createWallet(wallet);
        return "redirect:/wallets/user/" + userId;
    }
}

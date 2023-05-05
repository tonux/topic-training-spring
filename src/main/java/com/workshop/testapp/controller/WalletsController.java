package com.workshop.testapp.controller;

import com.workshop.testapp.domain.User;
import com.workshop.testapp.domain.Wallet;
import com.workshop.testapp.repositories.UserRepository;
import com.workshop.testapp.services.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    //Afficher la liste des wallets de l'utilisateur
    @GetMapping("/wallets/user/{id}")
    public String getUserWalletsByUserId(@PathVariable("id") Long id, Model model){
        List<Wallet> walletList = walletService.getUserWalletsByUserId(id);
        model.addAttribute("wallets", walletList);
        model.addAttribute("userId", id);
        return "wallet-list";
    }

    //Afficher le formulaire d'ajout d'un wallet
    @GetMapping("/newWalletForm/{userId}")
    public String form(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("wallet", new Wallet());
        model.addAttribute("action", "/addWallet/" +userId);
        return "wallet-form";
    }

    //Ajouter un wallet
    @PostMapping("/addWallet/{userId}")
    public String add(@PathVariable("userId") Long userId,Wallet wallet) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        wallet.setUser(user);
        walletService.createWallet(wallet);
        return "redirect:/wallets/user/" + userId;
    }

    //Delete wallet
    @GetMapping("/deleteWallet/{WalletId}")
    public String delete(@PathVariable("WalletId") Long WalletId) {
        Wallet wallet = walletService.getWalletById(WalletId);
        Long userId = wallet.getUser().getId();
        walletService.deleteWallet(WalletId);
        return "redirect:/wallets/user/" + userId;
    }

    //Afficher le formulaire de modification d'un wallet
    @GetMapping("/editWallet/{WalletId}")
    public String editForm(@PathVariable("WalletId") Long WalletId, Model model) {
        Wallet wallet = walletService.getWalletById(WalletId);
        model.addAttribute("wallet", wallet);
        model.addAttribute("action", "/updateWallet/" + WalletId);
        return "wallet-form";
    }

    //Modifier un wallet
    @PostMapping("/updateWallet/{WalletId}")
    public String update(@PathVariable("WalletId") Long WalletId, @ModelAttribute("wallet") Wallet walletForm) {
        Wallet wallet= walletService.getWalletById(WalletId);
        wallet.copy(walletForm);
        walletService.updateWallet(wallet);
        return "redirect:/wallets/user/" + wallet.getUser().getId();
    }
}

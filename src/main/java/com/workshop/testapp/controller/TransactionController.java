package com.workshop.testapp.controller;

import com.workshop.testapp.model.Transaction;
import com.workshop.testapp.services.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) { this.transactionService = transactionService; }

    //Afficher la liste des transactions d'un wallet
    @GetMapping("/transactions/{id}")
    public String getWalletTransactionsByWalletId(@PathVariable("id") Long id, Model model){
        List<Transaction> transactionList = transactionService.getWalletTransactionsByWalletId(id);
        model.addAttribute("transactions", transactionList);
        model.addAttribute("walletId", id);
        return "transaction-list";
    }


}

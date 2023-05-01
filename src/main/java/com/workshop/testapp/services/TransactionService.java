package com.workshop.testapp.services;

import com.workshop.testapp.model.Transaction;
import com.workshop.testapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    // create transaction
    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    //Get all transactions
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    //Get transaction by id
    public List<Transaction> getWalletTransactionsByWalletId(Long id) {
        List transactionList = transactionRepository.findAllByWalletId(id);
        return transactionList;
    }

    //Update transaction
    public void updateTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    //Delete transaction
    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

}

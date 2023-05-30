package com.workshop.testapp.repositories;

import com.workshop.testapp.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {


    //find wallet by id
    Wallet findById(int id);

    //wallet by User id
    List findAllByUserId(Long id);
}

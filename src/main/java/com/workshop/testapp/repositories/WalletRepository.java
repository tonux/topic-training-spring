package com.workshop.testapp.repositories;

import com.workshop.testapp.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {


    List findAllById(Long id);

    List findAllByUserId(Long id);
}

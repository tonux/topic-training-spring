package com.workshop.testapp.repositories;

import com.workshop.testapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List findAllByWalletId(Long id);

    @Query(value = "SELECT * FROM transactions WHERE wallet_id = ?1 ORDER BY date DESC", nativeQuery = true)
    List findAllByWalletIdByNativeSql(Long id);

    @Query("Select t from Transaction t where t.wallet.id = ?1 order by t.date desc")
    List findAllByWalletIdBySql(Long id);
}

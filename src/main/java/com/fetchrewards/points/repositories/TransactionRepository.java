package com.fetchrewards.points.repositories;

import com.fetchrewards.points.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //Returns all the transactions in sorted order based on timestamp.
    Iterable<Transaction> findAllByOrderByTimestampAsc();
}

package com.sanjeet.expensetracker.repository;

import com.sanjeet.expensetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdOrderByDateDesc(Long userId);
    List<Transaction> findByUserIdAndType(Long userId, String type);
}
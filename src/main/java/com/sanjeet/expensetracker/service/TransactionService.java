package com.sanjeet.expensetracker.service;

import com.sanjeet.expensetracker.dto.TransactionRequest;
import com.sanjeet.expensetracker.model.*;
import com.sanjeet.expensetracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository txnRepo;
    private final UserRepository userRepo;

    private User getUser(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Transaction> getAll(String email) {
        return txnRepo.findByUserIdOrderByDateDesc(getUser(email).getId());
    }

    public Transaction add(TransactionRequest req, String email) {
        Transaction txn = Transaction.builder()
                .description(req.getDescription())
                .amount(req.getAmount())
                .type(req.getType())
                .category(req.getCategory())
                .date(req.getDate())
                .note(req.getNote())
                .user(getUser(email))
                .build();
        return txnRepo.save(txn);
    }

    public Transaction update(Long id, TransactionRequest req, String email) {
        Transaction txn = txnRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        if (!txn.getUser().getEmail().equals(email))
            throw new RuntimeException("Unauthorized");
        txn.setDescription(req.getDescription());
        txn.setAmount(req.getAmount());
        txn.setType(req.getType());
        txn.setCategory(req.getCategory());
        txn.setDate(req.getDate());
        txn.setNote(req.getNote());
        return txnRepo.save(txn);
    }

    public void delete(Long id, String email) {
        Transaction txn = txnRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        if (!txn.getUser().getEmail().equals(email))
            throw new RuntimeException("Unauthorized");
        txnRepo.delete(txn);
    }
}
package com.sanjeet.expensetracker.controller;

import com.sanjeet.expensetracker.dto.TransactionRequest;
import com.sanjeet.expensetracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService txnService;

    @GetMapping
    public ResponseEntity<?> getAll(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(txnService.getAll(user.getUsername()));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody TransactionRequest req,
                                 @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(txnService.add(req, user.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody TransactionRequest req,
                                    @AuthenticationPrincipal UserDetails user) {
        try {
            return ResponseEntity.ok(txnService.update(id, req, user.getUsername()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @AuthenticationPrincipal UserDetails user) {
        try {
            txnService.delete(id, user.getUsername());
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.sanjeet.expensetracker.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TransactionRequest {
    private String description;
    private Double amount;
    private String type;
    private String category;
    private LocalDate date;
    private String note;
}
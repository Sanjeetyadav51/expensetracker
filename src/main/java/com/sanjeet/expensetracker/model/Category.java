package com.sanjeet.expensetracker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String emoji;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
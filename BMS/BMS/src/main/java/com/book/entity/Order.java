package com.book.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime orderDate;

    // Constructors, getters, and setters
}

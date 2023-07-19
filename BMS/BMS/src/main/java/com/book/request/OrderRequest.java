package com.book.request;

import com.book.entity.Customer;
import lombok.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class OrderRequest {


        private Long id;

        private Customer customer;

        private LocalDate orderDate;

        // Constructors, getters, and setters
    }



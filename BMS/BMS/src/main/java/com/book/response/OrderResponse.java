package com.book.response;

import com.book.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class OrderResponse {


        private Long id;

        private Customer customer;

        private LocalDate orderDate;

        // Constructors, getters, and setters
    }



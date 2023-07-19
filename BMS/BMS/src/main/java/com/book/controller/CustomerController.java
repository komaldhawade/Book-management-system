package com.book.controller;


import com.book.request.CustomerRequest;
import com.book.response.CustomerResponse;
import com.book.service.CustomerService;
import com.book.utility.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomers(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse createCustomer = this.customerService.createCustomer(customerRequest);
        return new ResponseEntity<CustomerResponse>(createCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<CustomerResponse> customers = this.customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getCustomerByCustomerId(@RequestParam(value = "customerId", required = false) Long customerId) {
        CustomerResponse customerById = this.customerService.getCustomerByCustomerId(customerId);
        //List<CustomerResponse> customers = this.customerService.getAllCustomers();

        return new ResponseEntity<>(customerById, HttpStatus.OK);

    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@Valid @PathVariable Long customerId, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse updatedCustomer = this.customerService.updateCustomer(customerRequest, customerId);
        return new ResponseEntity<CustomerResponse>(updatedCustomer, HttpStatus.OK);
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long customerId) {
        this.customerService.deleteCustomer(customerId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Customer Deleted Successfully", true), HttpStatus.OK);
    }
}


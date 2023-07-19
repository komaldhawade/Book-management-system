package com.book.service;

import com.book.request.CustomerRequest;
import com.book.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerByCustomerId(Long customerId);

    CustomerResponse updateCustomer(CustomerRequest customerRequest, Long customerId);

    List<CustomerResponse> getCustomerByKeyword(String keyword);

    void deleteCustomer(Long customerId);
}

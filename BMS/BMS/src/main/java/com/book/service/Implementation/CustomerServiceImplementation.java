package com.book.service.Implementation;

import com.book.entity.Customer;
import com.book.repository.CustomerRepository;
import com.book.request.CustomerRequest;
import com.book.response.CustomerResponse;
import com.book.service.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImplementation implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerResponse createCustomer(@Valid CustomerRequest customerRequest) {
        Customer customer = this.modelMapper.map(customerRequest, Customer.class);
        return this.modelMapper.map(this.customerRepository.save(customer), CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return this.customerRepository.findAll().stream().map(e -> this.modelMapper.map(e, CustomerResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerByCustomerId(Long customerId) {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %d not found", customerId)));
        return this.modelMapper.map(customer, CustomerResponse.class);

    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest customerRequest, @Valid Long customerId) {
        CustomerResponse customerResponse = this.modelMapper.map(this.customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %d not found", customerId))), CustomerResponse.class);
        customerResponse.setName(customerRequest.getName());
        customerRequest.setAddress(customerRequest.getAddress());
        customerRequest.setContactInformation(customerRequest.getContactInformation());
        return this.modelMapper.map(this.customerRepository.save(this.modelMapper.map(customerResponse, Customer.class)), CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> getCustomerByKeyword(String keyword) {
        return this.customerRepository.findByKeyword(keyword).stream().map(source -> this.modelMapper.map(source, CustomerResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Long customerId) {
        this.customerRepository.delete(this.modelMapper.map(this.getCustomerByCustomerId(customerId), Customer.class));

    }

}


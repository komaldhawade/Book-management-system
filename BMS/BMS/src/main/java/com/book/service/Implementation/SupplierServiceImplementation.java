package com.book.service.Implementation;

import com.book.entity.Supplier;
import com.book.repository.SupplierRepository;
import com.book.request.SupplierRequest;
import com.book.response.SupplierResponse;
import com.book.service.SupplierService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImplementation implements SupplierService {
    
        @Autowired
        private SupplierRepository supplierRepository;


        @Autowired
        private ModelMapper modelMapper;

        @Override
        public SupplierResponse createSupplier(@Valid SupplierRequest customerRequest) {
            Supplier customer = this.modelMapper.map(customerRequest, Supplier.class);
            return this.modelMapper.map(this.supplierRepository.save(customer), SupplierResponse.class);
        }

        @Override
        public List<SupplierResponse> getAllSuppliers() {
            return this.supplierRepository.findAll().stream().map(e -> this.modelMapper.map(e, SupplierResponse.class)).collect(Collectors.toList());
        }

        @Override
        public SupplierResponse getSupplierBySupplierId(Long customerId) {
            Supplier customer = this.supplierRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Supplier with id %d not found", customerId)));
            return this.modelMapper.map(customer, SupplierResponse.class);

        }

        @Override
        public SupplierResponse updateSupplier(SupplierRequest customerRequest, @Valid Long customerId) {
            SupplierResponse customerResponse = this.modelMapper.map(this.supplierRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Supplier with id %d not found", customerId))), SupplierResponse.class);
//            customerResponse.setSupplierDate(customerRequest.getSupplierDate());
//            customerResponse.setCustomer(customerRequest.getCustomer());
//            customerResponse.setCustomer(this.customerRepository.findById(customerRequest.getCustomer().getId()).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %d not found", customerId))));
            return this.modelMapper.map(this.supplierRepository.save(this.modelMapper.map(customerResponse, Supplier.class)), SupplierResponse.class);
        }

        @Override
        public List<SupplierResponse> getSupplierByKeyword(String keyword) {
            return this.supplierRepository.findByKeyword(keyword).stream().map(source -> this.modelMapper.map(source, SupplierResponse.class)).collect(Collectors.toList());
        }

        @Override
        public void deleteSupplier(Long customerId) {
            this.supplierRepository.delete(this.modelMapper.map(this.getSupplierBySupplierId(customerId), Supplier.class));

        }

    @Override
    public SupplierResponse upateSupplier(SupplierRequest supplierRequest, Long supplierId) {
        return null;
    }

}




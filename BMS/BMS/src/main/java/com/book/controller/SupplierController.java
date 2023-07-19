package com.book.controller;


import com.book.request.SupplierRequest;
import com.book.response.SupplierResponse;
import com.book.service.SupplierService;
import com.book.utility.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@Slf4j
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("/create")
    public ResponseEntity<SupplierResponse> createSuppliers(@Valid @RequestBody SupplierRequest supplierRequest) {
        SupplierResponse createSupplier = this.supplierService.createSupplier(supplierRequest);
        return new ResponseEntity<>(createSupplier, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllSuppliers() {
        List<SupplierResponse> suppliers = this.supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getSupplierBySupplierId(@RequestParam(value = "supplierId", required = false) Long supplierId) {
        SupplierResponse supplierById = this.supplierService.getSupplierBySupplierId(supplierId);
        //List<SupplierResponse> suppliers = this.supplierService.getAllSuppliers();

        return new ResponseEntity<>(supplierById, HttpStatus.OK);

    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<SupplierResponse> updateSupplier(@Valid @PathVariable Long supplierId, @RequestBody SupplierRequest supplierRequest) {
        SupplierResponse updatedSupplier = this.supplierService.upateSupplier(supplierRequest, supplierId);
        return new ResponseEntity<SupplierResponse>(updatedSupplier, HttpStatus.OK);
    }


    @DeleteMapping("/{supplierId}")
    public ResponseEntity<ApiResponse> deleteSupplier(@PathVariable Long supplierId) {
        this.supplierService.deleteSupplier(supplierId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Supplier Deleted Successfully", true), HttpStatus.OK);
    }
}

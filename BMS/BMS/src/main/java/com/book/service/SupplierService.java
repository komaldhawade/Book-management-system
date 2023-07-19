package com.book.service;

import com.book.request.SupplierRequest;
import com.book.response.SupplierResponse;

import java.util.List;

public interface SupplierService {
    SupplierResponse createSupplier(SupplierRequest supplierRequest);

    List<SupplierResponse> getAllSuppliers();

    SupplierResponse getSupplierBySupplierId(Long supplierId);

    SupplierResponse updateSupplier(SupplierRequest supplierRequest, Long supplierId);

    List<SupplierResponse> getSupplierByKeyword(String keyword);

    void deleteSupplier(Long supplierId);

    SupplierResponse upateSupplier(SupplierRequest supplierRequest, Long supplierId);
}

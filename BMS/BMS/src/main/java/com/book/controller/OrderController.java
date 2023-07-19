package com.book.controller;


import com.book.request.OrderRequest;
import com.book.response.OrderResponse;
import com.book.service.OrderService;
import com.book.utility.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrders(@Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse createOrder = this.orderService.createOrder(orderRequest);
        return new ResponseEntity<OrderResponse>(createOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<OrderResponse> orders = this.orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getOrderByOrderId(@RequestParam(value = "orderId", required = false) Long orderId) {
        OrderResponse orderById = this.orderService.getOrderByOrderId(orderId);
        //List<OrderResponse> orders = this.orderService.getAllOrders();

        return new ResponseEntity<>(orderById, HttpStatus.OK);

    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(@Valid @PathVariable Long orderId, @RequestBody OrderRequest orderRequest) {
        OrderResponse updatedOrder = this.orderService.updateOrder(orderRequest, orderId);
        return new ResponseEntity<OrderResponse>(updatedOrder, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> getOrderByKeyword(@PathVariable String keyword) {
        List<OrderResponse> searchedOrder = this.orderService.getOrderByKeyword(keyword);
        return new ResponseEntity<>((!searchedOrder.isEmpty()) ? searchedOrder
                : new ApiResponse("Order not found with keyword: " + keyword, true), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId) {
        this.orderService.deleteOrder(orderId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Order Deleted Successfully", true), HttpStatus.OK);
    }
}


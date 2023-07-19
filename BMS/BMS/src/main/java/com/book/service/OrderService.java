package com.book.service;

import com.book.request.OrderRequest;
import com.book.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderByOrderId(Long orderId);

    OrderResponse upateOrder(OrderRequest orderRequest, Long orderId);

    List<OrderResponse> getOrderByKeyword(String keyword);

    void deleteOrder(Long orderId);

    OrderResponse updateOrder(OrderRequest orderRequest, Long orderId);
}

package com.book.service.Implementation;

import com.book.entity.Order;
import com.book.repository.CustomerRepository;
import com.book.repository.OrderRepository;
import com.book.request.OrderRequest;
import com.book.response.OrderResponse;
import com.book.service.OrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class OrderServiceImplementation implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderResponse createOrder(@Valid OrderRequest customerRequest) {
        Order customer = this.modelMapper.map(customerRequest, Order.class);
        return this.modelMapper.map(this.orderRepository.save(customer), OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return this.orderRepository.findAll().stream().map(e -> this.modelMapper.map(e, OrderResponse.class)).collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderByOrderId(Long customerId) {
        Order customer = this.orderRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Order with id %d not found", customerId)));
        return this.modelMapper.map(customer, OrderResponse.class);

    }

    @Override
    public OrderResponse upateOrder(OrderRequest customerRequest, @Valid Long customerId) {
        OrderResponse customerResponse = this.modelMapper.map(this.orderRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Order with id %d not found", customerId))), OrderResponse.class);
        customerResponse.setOrderDate(customerRequest.getOrderDate());
        customerResponse.setCustomer(customerRequest.getCustomer());
        customerResponse.setCustomer(this.customerRepository.findById(customerRequest.getCustomer().getId()).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %d not found", customerId))));
        return this.modelMapper.map(this.orderRepository.save(this.modelMapper.map(customerResponse, Order.class)), OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getOrderByKeyword(String keyword) {
        return this.orderRepository.findByKeyword(keyword).stream().map(source -> this.modelMapper.map(source, OrderResponse.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long customerId) {
        this.orderRepository.delete(this.modelMapper.map(this.getOrderByOrderId(customerId), Order.class));

    }

    @Override
    public OrderResponse updateOrder(OrderRequest orderRequest, Long orderId) {
        return null;
    }

}


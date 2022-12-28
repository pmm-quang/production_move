package com.example.backend.service;

import com.example.backend.payload.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrder();
    List<OrderDto> getOrdersByCustomerId(String customerID);
    List<OrderDto> getOrdersByProductId(String productID);
    OrderDto createOrder(OrderDto orderDto, String customerID, String productID);
    OrderDto updateOrder(OrderDto orderDto, Long orderID);
    void deleteOrder(Long orderID);

}

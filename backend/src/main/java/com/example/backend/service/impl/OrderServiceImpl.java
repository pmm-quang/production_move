package com.example.backend.service.impl;

import com.example.backend.entities.Customer;
import com.example.backend.entities.Order;
import com.example.backend.entities.Product;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.OrderDto;
import com.example.backend.repository.CustomerRepo;
import com.example.backend.repository.OrderRepo;
import com.example.backend.repository.ProductRepo;
import com.example.backend.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<OrderDto> getAllOrder() {
        List<Order> orders = orderRepo.findAll();
        List<OrderDto> orderDtos = orders.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(String customerID) {
        Customer customer = customerRepo.findById(customerID).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "CustomerID", Long.parseLong(customerID))
        );
        List<Order> orders = orderRepo.findByCustomer(customer);
        List<OrderDto> orderDtos = orders.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public List<OrderDto> getOrdersByProductId(String productID) {
        Product product = productRepo.findById(productID).orElseThrow(
                ()-> new ResourceNotFoundException("Product", "ProductID", 12345)
        );
        List<Order> orders = orderRepo.findByProduct(product);
        List<OrderDto> orderDtos = orders.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto, String customerID, String productID) {
        Customer customer = customerRepo.findById(customerID).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "CustomerID", Long.parseLong(customerID))
        );
        Product product = productRepo.findById(productID).orElseThrow(
                ()-> new ResourceNotFoundException("Product", "ProductID", 12345)
        );
        Order order = mapper.map(orderDto, Order.class);
        Order newOrder = orderRepo.save(order);
        return orderDto;
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, Long orderID) {
        Order or = orderRepo.findById(orderID).orElseThrow(
                ()-> new ResourceNotFoundException("Order", "OrderID", orderID)
        );
        Order order = mapper.map(orderDto, Order.class);
        Order updateOrder = orderRepo.save(order);
        return orderDto;
    }

    @Override
    public void deleteOrder(Long orderID) {
        Order order = orderRepo.findById(orderID).orElseThrow(
                ()-> new ResourceNotFoundException("Order", "OrderID", orderID)
        );
        orderRepo.delete(order);
    }
}

package com.example.backend.service.impl;

import com.example.backend.entities.Customer;
import com.example.backend.entities.Order;
import com.example.backend.entities.Product;
import com.example.backend.entities.custom.OrderByYear;
import com.example.backend.entities.custom.OrderPerMonth;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.OrderDto;
import com.example.backend.repository.CustomerRepo;
import com.example.backend.repository.OrderRepo;
import com.example.backend.repository.ProductRepo;
import com.example.backend.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        order.setCustomer(customer);
        order.setProduct(product);
        Order newOrder = orderRepo.save(order);
        return mapper.map(newOrder, OrderDto.class);
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

    @Override
    public List<OrderPerMonth> getOrderPerMonthOfShop(Integer year) {
        Long shopID = Long.valueOf(3);
        List<OrderPerMonth> orderPerMonths = orderRepo.countOrdersPerMonthOfShop(year, shopID);
        List<OrderPerMonth> list1 = formatListOrderPerMonth(orderPerMonths);
        return list1;
    }

    @Override
    public List<OrderByYear> getOrderPerYearOfShop() {
        Long shopID = Long.valueOf(3);
        List<OrderByYear> orderByYears = orderRepo.countOrdersPerYearOfShop(shopID);
        return orderByYears;
    }

    @Override
    public List<OrderPerMonth> getOrderPerMonthOfFactory(Integer year) {
        Long factoryID = Long.valueOf(1);
        List<OrderPerMonth> list = orderRepo.countOrdersPerMonthOfFactory(year, factoryID);
        List<OrderPerMonth> list1 = formatListOrderPerMonth(list);
        return list1;
    }

    @Override
    public List<OrderByYear> getOrderPerYearOfFactory() {
        Long factoryID = Long.valueOf(1);
        List<OrderByYear> list = orderRepo.countOrdersPerYearOfFactory(factoryID);
        return list;
    }

    private List<OrderPerMonth> formatListOrderPerMonth(List<OrderPerMonth> list) {
        List<OrderPerMonth> list1 = new ArrayList<>();
        int j = 0;
        for(int i = 1; i <= 12; i++) {
            String s = "" + list.get(j).getMonth();
            String[] a = s.split(" ");
            int tmp = Integer.parseInt(a[1]);
            if (tmp == i) {
                list1.add(list.get(j));
                j++;
            }
            else {
                OrderPerMonth order = new OrderPerMonth(i, Long.valueOf(0));
                list1.add(order);
            }

        }
        return list1;
    }

}

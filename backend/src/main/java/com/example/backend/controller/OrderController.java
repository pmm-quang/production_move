package com.example.backend.controller;

import com.example.backend.entities.custom.OrderByYear;
import com.example.backend.entities.custom.OrderPerMonth;
import com.example.backend.payload.OrderDto;
import com.example.backend.service.OrderService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/order/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> getAllOrder() {
        List<OrderDto> orderDtos = orderService.getAllOrder();
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerID}/")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable String customerID) {
        List<OrderDto> orderDtos = orderService.getOrdersByCustomerId(customerID);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("/product/{productID}/")
    public ResponseEntity<List<OrderDto>> getOrdersByProduct(@PathVariable String productID) {
        List<OrderDto> orderDtos = orderService.getOrdersByCustomerId(productID);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @PostMapping("/customer/{customerID}/product/{productID}/add/")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto,
                                                @PathVariable String customerID,
                                                @PathVariable String productID) {
        OrderDto orderDto1 = orderService.createOrder(orderDto, customerID, productID);
        return new ResponseEntity<>(orderDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{orderID}/")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto,
                                                @PathVariable Long orderID) {
        OrderDto orderDto1 = orderService.updateOrder(orderDto, orderID);
        return new ResponseEntity<>(orderDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderID}/")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderID) {
        orderService.deleteOrder(orderID);
        return new ResponseEntity<>("Delete!!!", HttpStatus.OK);
    }

    @GetMapping("statistics/shop/permonth/{year}/")
    public ResponseEntity<List<OrderPerMonth>> getOrderPerMonthOfShop(@PathVariable Integer year) {
        List<OrderPerMonth> list = orderService.getOrderPerMonthOfShop(year);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("statistics/shop/peryear/")
    public ResponseEntity<List<OrderByYear>> getOrderPerYearOfShop() {
        List<OrderByYear> list = orderService.getOrderPerYearOfShop();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("statistics/factory/permonth/{year}/")
    public ResponseEntity<List<OrderPerMonth>> getOrderPerMonthOfFactory(@PathVariable Integer year) {
        List<OrderPerMonth> list = orderService.getOrderPerMonthOfFactory(year);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("statistics/factory/peryear/")
    public ResponseEntity<List<OrderByYear>> getOrderPerYearOfFactory() {
        List<OrderByYear> list = orderService.getOrderPerYearOfFactory();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

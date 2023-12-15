package com.alex.demo.lojapedidos.controller;

import com.alex.demo.lojapedidos.model.Order;
import com.alex.demo.lojapedidos.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(service.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<>(service.getOrderByCustomerId(id), HttpStatus.OK);
    }
}

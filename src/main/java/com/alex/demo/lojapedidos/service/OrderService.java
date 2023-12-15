package com.alex.demo.lojapedidos.service;

import com.alex.demo.lojapedidos.exceptions.CustomerNotFoundException;
import com.alex.demo.lojapedidos.model.Order;
import com.alex.demo.lojapedidos.model.enums.Status;
import com.alex.demo.lojapedidos.repository.CustomerRepository;
import com.alex.demo.lojapedidos.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(Order order, Long customerId) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow( () -> new CustomerNotFoundException("Customer not found."));

        order.setCustomerId(customer);
        order.setStatus(Status.CREATED);
        order.setDate(LocalDate.now());

        return orderRepository.save(order);
    }
}

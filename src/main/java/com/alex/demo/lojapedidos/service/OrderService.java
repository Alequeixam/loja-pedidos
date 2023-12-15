package com.alex.demo.lojapedidos.service;

import com.alex.demo.lojapedidos.exceptions.ResourceNotFoundException;
import com.alex.demo.lojapedidos.model.Customer;
import com.alex.demo.lojapedidos.model.Order;
import com.alex.demo.lojapedidos.model.enums.Status;
import com.alex.demo.lojapedidos.repository.CustomerRepository;
import com.alex.demo.lojapedidos.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomerId().getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", order.getCustomerId().getCustomerId()));

        order.setStatus(Status.CREATED);
        order.setDate(LocalDate.now());

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public List<Order> getOrderByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        return orderRepository.findByCustomerId_CustomerId(id);
    }
}

package com.alex.demo.lojapedidos.service;

import com.alex.demo.lojapedidos.exceptions.ResourceNotFoundException;
import com.alex.demo.lojapedidos.model.Payment;
import com.alex.demo.lojapedidos.model.enums.Status;
import com.alex.demo.lojapedidos.repository.OrderRepository;
import com.alex.demo.lojapedidos.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public Payment create(Payment payment) {
        var order = orderRepository.findById(payment.getOrderId().getOrderId())
                .orElseThrow( () -> new ResourceNotFoundException("Order", "id", payment.getOrderId().getOrderId()));

        order.setStatus(Status.CONFIRMED);
        payment.setPaymentDate(LocalDate.now());
        return paymentRepository.save(payment);
    }
}

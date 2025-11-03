package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.PaymentRequest;
import com.example.fooddelivery.exception.ResourceNotFoundException;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.Payment;
import com.example.fooddelivery.model.PaymentStatus;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final MockPaymentService mockPaymentService;
    
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, 
                         OrderRepository orderRepository,
                         MockPaymentService mockPaymentService) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.mockPaymentService = mockPaymentService;
    }
    
    @Transactional
    public Payment processPayment(Long orderId, PaymentRequest paymentRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        
        if (order.getPayment() != null) {
            throw new IllegalStateException("Payment already processed for this order");
        }
        
        double totalAmount = order.getOrderItems().stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
        
        totalAmount += 2.0; // Delivery fee
        
        PaymentStatus status = mockPaymentService.processPayment(
                paymentRequest.getPaymentMethod(), 
                totalAmount
        );
        
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(totalAmount);
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setPaymentStatus(status);
        payment.setPaymentDate(LocalDateTime.now());
        
        return paymentRepository.save(payment);
    }
    
    @Transactional(readOnly = true)
    public Payment getPaymentByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return paymentRepository.findByOrder(order)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for this order"));
    }
}
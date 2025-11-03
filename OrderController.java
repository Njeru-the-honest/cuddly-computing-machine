package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.OrderRequest;
import com.example.fooddelivery.dto.PaymentRequest;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.Payment;
import com.example.fooddelivery.service.OrderService;
import com.example.fooddelivery.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    
    private final OrderService orderService;
    private final PaymentService paymentService;
    
    @Autowired
    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }
    
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderRequest orderRequest,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        Order order = orderService.createOrder(orderRequest, customerEmail);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/{id}/track")
    public ResponseEntity<Order> trackOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
    @PostMapping("/{id}/pay")
    public ResponseEntity<Payment> processPayment(
            @PathVariable Long id,
            @RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.processPayment(id, paymentRequest);
        return ResponseEntity.ok(payment);
    }
    
    @GetMapping("/{id}/payment")
    public ResponseEntity<Payment> getPaymentDetails(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentByOrderId(id);
        return ResponseEntity.ok(payment);
    }
}
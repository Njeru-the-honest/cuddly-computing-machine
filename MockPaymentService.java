package com.example.fooddelivery.service;

import com.example.fooddelivery.model.PaymentMethod;
import com.example.fooddelivery.model.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MockPaymentService {
    
    private final Random random = new Random();
    
    public PaymentStatus processPayment(PaymentMethod paymentMethod, Double amount) {
        // Simulate payment processing delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simulate 80% success rate
        boolean isSuccessful = random.nextDouble() < 0.8;
        
        if (paymentMethod == PaymentMethod.COD) {
            return PaymentStatus.PENDING;
        }
        
        return isSuccessful ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }
}
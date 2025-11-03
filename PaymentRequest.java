package com.example.fooddelivery.dto;

import com.example.fooddelivery.model.PaymentMethod;

public class PaymentRequest {
    
    private PaymentMethod paymentMethod;
    
    // Constructors
    public PaymentRequest() {
    }
    
    public PaymentRequest(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    // Getters and Setters
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
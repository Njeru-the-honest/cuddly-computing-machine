package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.FeedbackRequest;
import com.example.fooddelivery.exception.ResourceNotFoundException;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.model.Feedback;
import com.example.fooddelivery.model.Restaurant;
import com.example.fooddelivery.repository.CustomerRepository;
import com.example.fooddelivery.repository.FeedbackRepository;
import com.example.fooddelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {
    
    private final FeedbackRepository feedbackRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    
    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository,
                          CustomerRepository customerRepository,
                          RestaurantRepository restaurantRepository) {
        this.feedbackRepository = feedbackRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
    }
    
    @Transactional
    public Feedback createFeedback(FeedbackRequest feedbackRequest, String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        
        Restaurant restaurant = restaurantRepository.findById(feedbackRequest.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        
        Feedback feedback = new Feedback();
        feedback.setCustomer(customer);
        feedback.setRestaurant(restaurant);
        feedback.setRating(feedbackRequest.getRating());
        feedback.setComment(feedbackRequest.getComment());
        feedback.setDate(LocalDateTime.now());
        
        return feedbackRepository.save(feedback);
    }
    
    @Transactional(readOnly = true)
    public List<Feedback> getFeedbacksByRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        return feedbackRepository.findByRestaurant(restaurant);
    }
    
    @Transactional(readOnly = true)
    public List<Feedback> getFeedbacksByCustomer(String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return feedbackRepository.findByCustomer(customer);
    }
}
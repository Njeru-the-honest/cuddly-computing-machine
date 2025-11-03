package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.FeedbackRequest;
import com.example.fooddelivery.model.Feedback;
import com.example.fooddelivery.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {
    
    private final FeedbackService feedbackService;
    
    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(
            @RequestBody FeedbackRequest feedbackRequest,
            Authentication authentication) {
        String customerEmail = authentication.getName();
        Feedback feedback = feedbackService.createFeedback(feedbackRequest, customerEmail);
        return ResponseEntity.ok(feedback);
    }
    
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Feedback>> getRestaurantFeedbacks(@PathVariable Long restaurantId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByRestaurant(restaurantId);
        return ResponseEntity.ok(feedbacks);
    }
}
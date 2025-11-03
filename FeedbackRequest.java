package com.example.fooddelivery.dto;

public class FeedbackRequest {
    
    private Long restaurantId;
    private Integer rating;
    private String comment;
    
    // Constructors
    public FeedbackRequest() {
    }
    
    public FeedbackRequest(Long restaurantId, Integer rating, String comment) {
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
    }
    
    // Getters and Setters
    public Long getRestaurantId() {
        return restaurantId;
    }
    
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
}
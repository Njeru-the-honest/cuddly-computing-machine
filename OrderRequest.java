package com.example.fooddelivery.dto;

import java.util.List;

public class OrderRequest {
    
    private Long restaurantId;
    private List<OrderItemRequest> items;
    
    // Constructors
    public OrderRequest() {
    }
    
    public OrderRequest(Long restaurantId, List<OrderItemRequest> items) {
        this.restaurantId = restaurantId;
        this.items = items;
    }
    
    // Getters and Setters
    public Long getRestaurantId() {
        return restaurantId;
    }
    
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public List<OrderItemRequest> getItems() {
        return items;
    }
    
    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
    
    // Inner class for order items
    public static class OrderItemRequest {
        private Long menuItemId;
        private Integer quantity;
        
        public OrderItemRequest() {
        }
        
        public OrderItemRequest(Long menuItemId, Integer quantity) {
            this.menuItemId = menuItemId;
            this.quantity = quantity;
        }
        
        public Long getMenuItemId() {
            return menuItemId;
        }
        
        public void setMenuItemId(Long menuItemId) {
            this.menuItemId = menuItemId;
        }
        
        public Integer getQuantity() {
            return quantity;
        }
        
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}
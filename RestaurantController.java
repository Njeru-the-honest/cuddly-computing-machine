package com.example.fooddelivery.controller;

import com.example.fooddelivery.model.MenuItem;
import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.OrderStatus;
import com.example.fooddelivery.model.Restaurant;
import com.example.fooddelivery.service.OrderService;
import com.example.fooddelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/restaurants")
@CrossOrigin(origins = "*")
public class RestaurantController {
    
    private final RestaurantService restaurantService;
    private final OrderService orderService;
    
    @Autowired
    public RestaurantController(RestaurantService restaurantService, OrderService orderService) {
        this.restaurantService = restaurantService;
        this.orderService = orderService;
    }
    
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }
    
    @GetMapping("/{id}/menu")
    public ResponseEntity<List<MenuItem>> getRestaurantMenu(@PathVariable Long id) {
        List<MenuItem> menuItems = restaurantService.getMenuItemsByRestaurantId(id);
        return ResponseEntity.ok(menuItems);
    }
    
    @GetMapping("/{id}/orders")
    public ResponseEntity<List<Order>> getRestaurantOrders(@PathVariable Long id) {
        List<Order> orders = orderService.getOrdersByRestaurant(id);
        return ResponseEntity.ok(orders);
    }
    
    @PutMapping("/{restaurantId}/menu/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable Long restaurantId,
            @PathVariable Long itemId,
            @RequestBody MenuItem menuItem) {
        MenuItem updatedItem = restaurantService.updateMenuItem(restaurantId, itemId, menuItem);
        return ResponseEntity.ok(updatedItem);
    }
    
    @PutMapping("/orders/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody Map<String, String> statusUpdate) {
        OrderStatus status = OrderStatus.valueOf(statusUpdate.get("status"));
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updatedOrder);
    }
}
package com.example.fooddelivery.service;

import com.example.fooddelivery.exception.ResourceNotFoundException;
import com.example.fooddelivery.model.MenuItem;
import com.example.fooddelivery.model.Restaurant;
import com.example.fooddelivery.repository.MenuItemRepository;
import com.example.fooddelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {
    
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
    }
    
    @Transactional(readOnly = true)
    public List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        return menuItemRepository.findByRestaurant(restaurant);  // FIXED: Changed from findByRestaurantId
    }
    
    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
    
    @Transactional
    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setName(restaurantDetails.getName());
        restaurant.setLocation(restaurantDetails.getLocation());
        restaurant.setContactInfo(restaurantDetails.getContactInfo());
        return restaurantRepository.save(restaurant);
    }
    
    @Transactional
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = getRestaurantById(id);
        restaurantRepository.delete(restaurant);
    }
    
    @Transactional
    public MenuItem updateMenuItem(Long restaurantId, Long itemId, MenuItem menuItemDetails) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id: " + itemId));
        
        if (!menuItem.getRestaurant().getId().equals(restaurantId)) {
            throw new IllegalArgumentException("Menu item does not belong to this restaurant");
        }
        
        menuItem.setName(menuItemDetails.getName());
        menuItem.setDescription(menuItemDetails.getDescription());
        menuItem.setPrice(menuItemDetails.getPrice());
        menuItem.setDiscountPercentage(menuItemDetails.getDiscountPercentage());
        
        return menuItemRepository.save(menuItem);
    }
}
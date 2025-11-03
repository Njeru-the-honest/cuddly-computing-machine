package com.example.fooddelivery.config;

import com.example.fooddelivery.model.*;
import com.example.fooddelivery.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public DataLoader(UserRepository userRepository,
                     CustomerRepository customerRepository,
                     RestaurantRepository restaurantRepository,
                     MenuItemRepository menuItemRepository,
                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (userRepository.count() > 0) {
            return;
        }
        
        // Create Admin User
        User admin = new User();
        admin.setName("Admin User");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
        
        // Create Customer Users
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john@example.com");
        customer1.setPassword(passwordEncoder.encode("password123"));
        customer1.setRole(Role.CUSTOMER);
        customer1.setAddress("123 Main St, Nairobi");
        customer1.setPhoneNumber("+254700000001");
        customerRepository.save(customer1);
        
        Customer customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("jane@example.com");
        customer2.setPassword(passwordEncoder.encode("password123"));
        customer2.setRole(Role.CUSTOMER);
        customer2.setAddress("456 Oak Ave, Nairobi");
        customer2.setPhoneNumber("+254700000002");
        customerRepository.save(customer2);
        
        // Create Restaurants
        Restaurant restaurant1 = new Restaurant("Pizza Palace", "Downtown Nairobi", "+254701234567");
        restaurantRepository.save(restaurant1);
        
        Restaurant restaurant2 = new Restaurant("Burger House", "Westlands, Nairobi", "+254702345678");
        restaurantRepository.save(restaurant2);
        
        Restaurant restaurant3 = new Restaurant("Sushi World", "Karen, Nairobi", "+254703456789");
        restaurantRepository.save(restaurant3);
        
        // Create Menu Items for Pizza Palace
        MenuItem pizza1 = new MenuItem();
        pizza1.setName("Margherita Pizza");
        pizza1.setDescription("Classic tomato and mozzarella");
        pizza1.setPrice(12.99);
        pizza1.setDiscountPercentage(0.0);
        pizza1.setRestaurant(restaurant1);
        menuItemRepository.save(pizza1);
        
        MenuItem pizza2 = new MenuItem();
        pizza2.setName("Pepperoni Pizza");
        pizza2.setDescription("Spicy pepperoni with cheese");
        pizza2.setPrice(15.99);
        pizza2.setDiscountPercentage(10.0);
        pizza2.setRestaurant(restaurant1);
        menuItemRepository.save(pizza2);
        
        MenuItem pizza3 = new MenuItem();
        pizza3.setName("Veggie Supreme");
        pizza3.setDescription("Loaded with fresh vegetables");
        pizza3.setPrice(14.99);
        pizza3.setDiscountPercentage(0.0);
        pizza3.setRestaurant(restaurant1);
        menuItemRepository.save(pizza3);
        
        // Create Menu Items for Burger House
        MenuItem burger1 = new MenuItem();
        burger1.setName("Classic Burger");
        burger1.setDescription("Beef patty with lettuce and tomato");
        burger1.setPrice(8.99);
        burger1.setDiscountPercentage(0.0);
        burger1.setRestaurant(restaurant2);
        menuItemRepository.save(burger1);
        
        MenuItem burger2 = new MenuItem();
        burger2.setName("Cheese Burger");
        burger2.setDescription("Double cheese with bacon");
        burger2.setPrice(10.99);
        burger2.setDiscountPercentage(15.0);
        burger2.setRestaurant(restaurant2);
        menuItemRepository.save(burger2);
        
        MenuItem burger3 = new MenuItem();
        burger3.setName("Chicken Burger");
        burger3.setDescription("Grilled chicken with special sauce");
        burger3.setPrice(9.99);
        burger3.setDiscountPercentage(0.0);
        burger3.setRestaurant(restaurant2);
        menuItemRepository.save(burger3);
        
        // Create Menu Items for Sushi World
        MenuItem sushi1 = new MenuItem();
        sushi1.setName("California Roll");
        sushi1.setDescription("Crab, avocado, and cucumber");
        sushi1.setPrice(11.99);
        sushi1.setDiscountPercentage(0.0);
        sushi1.setRestaurant(restaurant3);
        menuItemRepository.save(sushi1);
        
        MenuItem sushi2 = new MenuItem();
        sushi2.setName("Salmon Nigiri");
        sushi2.setDescription("Fresh salmon on rice");
        sushi2.setPrice(13.99);
        sushi2.setDiscountPercentage(20.0); sushi2.setRestaurant(restaurant3); menuItemRepository.save(sushi2);

        MenuItem sushi3 = new MenuItem();
        sushi3.setName("Tuna Sashimi");
        sushi3.setDescription("Premium tuna slices");
        sushi3.setPrice(16.99);
        sushi3.setDiscountPercentage(0.0);
        sushi3.setRestaurant(restaurant3);
        menuItemRepository.save(sushi3);
    
        System.out.println("Sample data loaded successfully!");
}

}
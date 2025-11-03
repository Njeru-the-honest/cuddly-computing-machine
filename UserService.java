package com.example.fooddelivery.service;

import com.example.fooddelivery.dto.RegisterRequest;
import com.example.fooddelivery.exception.ResourceNotFoundException;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.model.Role;
import com.example.fooddelivery.model.User;
import com.example.fooddelivery.repository.CustomerRepository;
import com.example.fooddelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, 
                      CustomerRepository customerRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    public User registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        
        // Check if role is CUSTOMER
        if (registerRequest.getRole() == Role.CUSTOMER) {
            Customer customer = new Customer();
            customer.setName(registerRequest.getName());
            customer.setEmail(registerRequest.getEmail());
            customer.setPassword(encodedPassword);
            customer.setRole(Role.CUSTOMER);
            customer.setAddress(registerRequest.getAddress());
            customer.setPhoneNumber(registerRequest.getPhoneNumber());
            return customerRepository.save(customer);
        } else {
            // For ADMIN or RESTAURANT roles
            User user = new User();
            user.setName(registerRequest.getName());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(encodedPassword);
            user.setRole(registerRequest.getRole());
            return userRepository.save(user);
        }
    }
    
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
    
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}
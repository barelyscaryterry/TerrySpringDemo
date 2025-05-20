package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.demo.model.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
    
    private final Map<Long, Order> orders = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    // Product prices (simulating a database)
    private final Map<String, Double> productPrices = new HashMap<>();
    
    public OrderService() {
        // Initialize product prices
        productPrices.put("Spring Boot Starter", 49.99);
        productPrices.put("Web Framework", 79.99);
        productPrices.put("Database Manager", 99.99);
        
        // Add some sample orders
        createOrder("John Doe", "Spring Boot Starter", 2);
        createOrder("Jane Smith", "Database Manager", 1);
    }
    
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    public Order getOrderById(Long id) {
        return orders.get(id);
    }
    
    public Order createOrder(String customerName, String productName, int quantity) {
        Long id = idGenerator.getAndIncrement();
        double price = productPrices.getOrDefault(productName, 0.0);
        double total = price * quantity;
        
        Order order = Order.builder()
                .id(id)
                .date(LocalDate.now())
                .customerName(customerName)
                .productName(productName)
                .quantity(quantity)
                .total(total)
                .status("Pending")
                .build();
        
        orders.put(id, order);
        log.info("Created new order: {}", order);
        return order;
    }
    
    public Order updateOrderStatus(Long id, String status) {
        Order order = orders.get(id);
        if (order != null) {
            order.setStatus(status);
            log.info("Updated order status: {} to {}", id, status);
        }
        return order;
    }
    
    public void deleteOrder(Long id) {
        orders.remove(id);
        log.info("Deleted order: {}", id);
    }
}

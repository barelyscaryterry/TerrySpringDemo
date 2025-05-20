package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Order;

public class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void shouldReturnAllOrders() {
        // When
        List<Order> orders = orderService.getAllOrders();
        
        // Then
        assertNotNull(orders);
        assertEquals(2, orders.size()); // Two sample orders are created in the constructor
    }

    @Test
    public void shouldGetOrderById() {
        // Given
        List<Order> orders = orderService.getAllOrders();
        Long id = orders.get(0).getId();
        
        // When
        Order order = orderService.getOrderById(id);
        
        // Then
        assertNotNull(order);
        assertEquals(id, order.getId());
    }

    @Test
    public void shouldCreateOrder() {
        // Given
        String customerName = "Test Customer";
        String productName = "Web Framework";
        int quantity = 3;
        
        // When
        Order order = orderService.createOrder(customerName, productName, quantity);
        
        // Then
        assertNotNull(order);
        assertEquals(customerName, order.getCustomerName());
        assertEquals(productName, order.getProductName());
        assertEquals(quantity, order.getQuantity());
        assertEquals(79.99 * quantity, order.getTotal(), 0.01); // Price * quantity
        assertEquals("Pending", order.getStatus());
        
        // Verify it was added to the list
        List<Order> orders = orderService.getAllOrders();
        assertTrue(orders.contains(order));
    }

    @Test
    public void shouldUpdateOrderStatus() {
        // Given
        List<Order> orders = orderService.getAllOrders();
        Long id = orders.get(0).getId();
        String newStatus = "Shipped";
        
        // When
        Order updatedOrder = orderService.updateOrderStatus(id, newStatus);
        
        // Then
        assertNotNull(updatedOrder);
        assertEquals(newStatus, updatedOrder.getStatus());
        
        // Verify it was updated in the list
        Order retrievedOrder = orderService.getOrderById(id);
        assertEquals(newStatus, retrievedOrder.getStatus());
    }

    @Test
    public void shouldDeleteOrder() {
        // Given
        List<Order> orders = orderService.getAllOrders();
        Long id = orders.get(0).getId();
        int initialCount = orders.size();
        
        // When
        orderService.deleteOrder(id);
        
        // Then
        List<Order> updatedOrders = orderService.getAllOrders();
        assertEquals(initialCount - 1, updatedOrders.size());
        assertNull(orderService.getOrderById(id));
    }
}

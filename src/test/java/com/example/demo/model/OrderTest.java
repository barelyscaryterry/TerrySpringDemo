package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void testOrderCreation() {
        // Given
        Long id = 1L;
        LocalDate date = LocalDate.now();
        String customerName = "John Doe";
        String productName = "Spring Boot Starter";
        int quantity = 2;
        double total = 99.98;
        String status = "Pending";
        
        // When
        Order order = Order.builder()
                .id(id)
                .date(date)
                .customerName(customerName)
                .productName(productName)
                .quantity(quantity)
                .total(total)
                .status(status)
                .build();
        
        // Then
        assertEquals(id, order.getId());
        assertEquals(date, order.getDate());
        assertEquals(customerName, order.getCustomerName());
        assertEquals(productName, order.getProductName());
        assertEquals(quantity, order.getQuantity());
        assertEquals(total, order.getTotal(), 0.001);
        assertEquals(status, order.getStatus());
    }
    
    @Test
    public void testOrderEquality() {
        // Given
        Order order1 = Order.builder()
                .id(1L)
                .date(LocalDate.now())
                .customerName("John Doe")
                .productName("Spring Boot Starter")
                .quantity(2)
                .total(99.98)
                .status("Pending")
                .build();
        
        Order order2 = Order.builder()
                .id(1L)
                .date(order1.getDate())
                .customerName("John Doe")
                .productName("Spring Boot Starter")
                .quantity(2)
                .total(99.98)
                .status("Pending")
                .build();
        
        Order differentOrder = Order.builder()
                .id(2L)
                .date(LocalDate.now())
                .customerName("Jane Smith")
                .productName("Database Manager")
                .quantity(1)
                .total(99.99)
                .status("Shipped")
                .build();
        
        // Then
        assertEquals(order1, order2);
        assertNotEquals(order1, differentOrder);
    }
    
    @Test
    public void testToString() {
        // Given
        Order order = Order.builder()
                .id(1L)
                .date(LocalDate.of(2025, 5, 19))
                .customerName("John Doe")
                .productName("Spring Boot Starter")
                .quantity(2)
                .total(99.98)
                .status("Pending")
                .build();
        
        // When
        String toString = order.toString();
        
        // Then
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("customerName=John Doe"));
        assertTrue(toString.contains("productName=Spring Boot Starter"));
        assertTrue(toString.contains("quantity=2"));
        assertTrue(toString.contains("total=99.98"));
        assertTrue(toString.contains("status=Pending"));
    }
}

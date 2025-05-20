package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.example.demo.service.OrderService;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;
    
    @MockBean
    private SpringTemplateEngine templateEngine;

    @Test
    public void shouldRedirectWhenOrderNotFound() throws Exception {
        // Given
        when(orderService.getOrderById(999L)).thenReturn(null);

        // When & Then
        mockMvc.perform(get("/orders/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));
    }

    @Test
    public void shouldCreateOrderAndRedirect() throws Exception {
        // Given
        String customerName = "New Customer";
        String productName = "Web Framework";
        int quantity = 3;
        
        // When & Then
        mockMvc.perform(post("/orders")
                .param("customerName", customerName)
                .param("productName", productName)
                .param("quantity", String.valueOf(quantity)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));
        
        verify(orderService).createOrder(customerName, productName, quantity);
    }
}

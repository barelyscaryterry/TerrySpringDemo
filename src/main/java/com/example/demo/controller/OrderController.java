package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping
    public String getAllOrders(Model model) {
        log.info("Fetching all orders");
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }
    
    @GetMapping("/{id}")
    public String getOrderById(@PathVariable Long id, Model model) {
        log.info("Fetching order with id: {}", id);
        Order order = orderService.getOrderById(id);
        if (order != null) {
            model.addAttribute("order", order);
            return "order-detail";
        } else {
            return "redirect:/orders";
        }
    }
    
    @PostMapping
    public String createOrder(
            @RequestParam String customerName,
            @RequestParam String productName,
            @RequestParam int quantity,
            Model model) {
        
        log.info("Creating new order for customer: {}, product: {}, quantity: {}", 
                customerName, productName, quantity);
        
        orderService.createOrder(customerName, productName, quantity);
        return "redirect:/orders";
    }
}

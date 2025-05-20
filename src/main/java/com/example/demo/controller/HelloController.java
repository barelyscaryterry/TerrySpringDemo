package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        log.info("Hello endpoint called");
        model.addAttribute("message", "Hello from TerrySpringDemo!");
        return "hello";
    }
    
    // API endpoint that returns raw text
    @GetMapping("/api/hello")
    @ResponseBody
    public String helloApi() {
        log.info("Hello API endpoint called");
        return "Hello from TerrySpringDemo API!";
    }
}

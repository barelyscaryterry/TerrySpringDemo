package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("Serving homepage");
        return "index";
    }
    
    @GetMapping("/about")
    public String about() {
        log.info("Serving about page");
        return "about";
    }
    
    @GetMapping("/contact")
    public String contact() {
        log.info("Serving contact page");
        return "contact";
    }
}

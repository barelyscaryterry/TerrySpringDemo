package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Add simple view controllers for pages that don't need controller logic
        registry.addViewController("/products/software").setViewName("products/software");
        registry.addViewController("/products/hardware").setViewName("products/hardware");
        registry.addViewController("/services/consulting").setViewName("services/consulting");
        registry.addViewController("/services/support").setViewName("services/support");
        registry.addViewController("/services/training").setViewName("services/training");
        registry.addViewController("/resources/docs").setViewName("resources/docs");
        registry.addViewController("/resources/tutorials").setViewName("resources/tutorials");
        registry.addViewController("/resources/downloads").setViewName("resources/downloads");
    }
}

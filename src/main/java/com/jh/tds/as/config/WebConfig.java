package com.jh.tds.as.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Apply CORS to the API endpoints
            .allowedOrigins("http://localhost:3000")  // Allow requests from your React frontend
            .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
            .allowedHeaders("*");  // Allow all headers
    }
}
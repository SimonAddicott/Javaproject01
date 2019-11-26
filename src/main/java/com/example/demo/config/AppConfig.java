package com.example.demo.config;

import com.example.demo.service.ProductService;
import controller.ProductController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ProductController productManagerApp() {
        return new ProductController(productService());
    }

    @Bean(name = "productService")
    public ProductService productService() {
        return new ProductService();
    }

}

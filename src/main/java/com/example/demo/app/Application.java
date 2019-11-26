package com.example.demo.app;

import com.example.demo.config.AppConfig;
import controller.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
@ComponentScan({
        "com.example.demo.rest",
        "com.example.demo.service"
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try (AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ProductController productManager = ctx.getBean(ProductController.class);
            productManager.run();
        }
    }
}

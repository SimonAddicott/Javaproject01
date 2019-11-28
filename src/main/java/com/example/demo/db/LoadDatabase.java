package com.example.demo.db;
import com.example.demo.business.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            System.out.println("Preloading " + repository.save(new Product("P01", "Product 01", 9.99)));
            System.out.println("Preloading " + repository.save(new Product("P02", "Product 02", 5.99)));
            System.out.println("Preloading " + repository.save(new Product("P03", "Product 03", 1.99)));
        };
    }
}
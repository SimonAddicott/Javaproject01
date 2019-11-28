package com.example.demo.rest;

import com.example.demo.business.Product;
import com.example.demo.db.DAOException;
import com.example.demo.db.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity listAll() {
        List<Product> products = this.productRepository.findAll();
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity listProduct(
            @PathVariable("id") String productId
    ) throws ProductNotFoundException {
        Product product = this.productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new ProductNotFoundException()
                );

        return new ResponseEntity(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity listProduct(
            @RequestParam("code") String code,
            @RequestParam("description") String description,
            @RequestParam("price") double price
            ) {
        this.productRepository.save(new Product(code, description, price));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(
            @PathVariable("id") String productId
    ) {
        this.productRepository.deleteById(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DAOException.class)
    public ResponseEntity handleException(DAOException e) {
        return new ResponseEntity("Sorry, that request went a bit wrong...", HttpStatus.NOT_FOUND);
    }
}

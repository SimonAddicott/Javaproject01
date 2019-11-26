package com.example.demo.rest;

import com.example.demo.business.Product;
import com.example.demo.service.WebProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {

    private WebProductService productService;

    public ProductRestController(WebProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity listAll() {
        List<Product> products = this.productService.gatherProducts();
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity listProduct(
            @PathVariable("id") String productId
    ) {
        Product product = this.productService.getProduct(productId);
        return new ResponseEntity(product, HttpStatus.OK);
    }
}

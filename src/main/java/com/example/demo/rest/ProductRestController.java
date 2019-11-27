package com.example.demo.rest;

import com.example.demo.business.Product;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

    private ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Read ALL
     * @return
     */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity listAll() {
        List<Product> products = this.productService.gatherProducts();
        return new ResponseEntity(products, HttpStatus.OK);
    }

    /**
     * Read SINGLE
     * @return
     */
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity listProduct(
            @PathVariable("id") String productId
    ) {
        Product product = this.productService.getProduct(productId);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    /**
     * Create
     * @return
     */
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity listProduct(
            @RequestParam("code") String code,
            @RequestParam("description") String description,
            @RequestParam("price") double price
            ) {
        if(!this.productService.addProductToFile(code, description, price)) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Delete
     */
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(
            @PathVariable("id") String productId
    ) {
        if(!this.productService.deleteProductFromFile(productId)) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

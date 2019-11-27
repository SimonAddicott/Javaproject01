package com.example.demo.service;

import com.example.demo.business.Product;
import com.example.demo.db.DAO;
import com.example.demo.db.DAOException;
import com.example.demo.db.ProductTextFile;
import com.example.demo.ui.Console;
import com.example.demo.ui.StringUtils;
import controller.ProductController;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static DAO<Product> file;

    public ProductService() {
        readInfile("products.txt");
    }

    private void readInfile(String filePath) {
        try {
            file = new ProductTextFile(filePath);
        } catch (DAOException e) {
            System.out.println("Error reading product data.");
            System.out.println(e.getMessage());
            System.out.println("Exiting application.\n");
            System.exit(0);
        }
    }

    /**
     * Data methods
     * @return
     */
    public List<Product> gatherProducts(){
        List<Product> products = new ArrayList<>();
        try {
            products = file.getAll();
        } catch (DAOException e) {
            System.out.println("cannot read file\n");
            // throw 500
        }
        return products;
    }

    public Product getProduct(String id) {
        Product product = new Product();
        try {
            product = file.get(id);

        } catch(DAOException e) {
            System.out.println(e.getMessage());
            // throw 404
        }
        return product;
    }

    /**
     * CLI Methods
     */
    public void displayAllProducts() {
        System.out.println("PRODUCT LIST");

        List<Product> products = gatherProducts();

        Product p;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            p = products.get(i);
            sb.append(StringUtils.padWithSpaces(
                    p.getCode(), 8));
            sb.append(StringUtils.padWithSpaces(
                    p.getDescription(), 44));
            sb.append(
                    p.getPriceFormatted());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public boolean addProductToFile(String code, String description, double price) {
        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);

        try {
            file.add(product);
        } catch (DAOException e) {
            System.out.println("Error adding product.\n");
            return false;
        }

        return true;
    }

    public boolean deleteProductFromFile(String productCode) {
        try {
            Product p = file.get(productCode);
            if (p == null) {
                return false;

            } else {
                file.delete(p);
                return true;

            }
        } catch (DAOException e) {
            return false;
        }
    }
}

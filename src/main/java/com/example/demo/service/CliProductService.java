package com.example.demo.service;

import com.example.demo.ui.Console;

public class CliProductService extends ProductService{

    public CliProductService() {
        super();
    }

    public boolean addProduct() {
        String code = Console.getString("Enter product code: ");
        String description = Console.getLine("Enter product description: ");
        double price = Console.getDouble("Enter price: ");

        if(!super.addProduct(code, description, price)){
            System.out.println(description + " failed to be added.\n");
            return false;
        }

        System.out.println(description + " has been added.\n");
        return true;
    }
}

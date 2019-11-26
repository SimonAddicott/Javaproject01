package controller;

import com.example.demo.service.ProductService;
import com.example.demo.ui.Console;

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
        System.out.println("Welcome to the Product Manager\n");
        displayMenu();
    }

    public void run() {
        // perform 1 or more actions
        String action = "";
        while (!action.equalsIgnoreCase("exit")) {
            action = Console.getString("Enter a command: ");
            System.out.println();
            // get the input from the user
            if (action.equalsIgnoreCase("list")) {
                productService.displayAllProducts();
            } else if (action.equalsIgnoreCase("add")) {
                productService.addProduct();
            } else if (action.equalsIgnoreCase("del") ||
                    action.equalsIgnoreCase("delete")) {
                productService.deleteProduct();
            } else if (action.equalsIgnoreCase("help") ||
                    action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit")) {
                System.out.println("Bye.\n");
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("list    - List all products");
        System.out.println("add     - Add a product");
        System.out.println("del     - Delete a product");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }
}
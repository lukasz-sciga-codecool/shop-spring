package com.codecool.shopspring.product;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<String> getProducts() {
        return List.of("iPhone 11", "iPhone 12 Turbo x");
    }

    @GetMapping("/branded-products")
    public List<String> getProductRequestParam(@RequestParam(required = false) final String brand) {
        if (brand != null) {
            if (brand.equals("iphone")) {
                return List.of("iPhone 11");
            } else {
                return List.of("Samsung");
            }
        } else {
            return List.of("iPhone 11", "Samsung");
        }
    }

    @GetMapping("/products/{productId}")
    public Product getProductPathVariable(@PathVariable final String productId) {
        if (productId.equals("iphone")) {
            return new Product("iPhone 11");
        } else {
            return new Product("Samsung Galaxy Milky way");
        }
    }
}

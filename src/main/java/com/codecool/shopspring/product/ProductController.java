package com.codecool.shopspring.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    public static final Product IPHONE_11_PRODUCT = new Product("11", "iPhone", 0L);
    public static final Product SAMSUNG_GALAXY_S20 = new Product("Galaxy S20", "Samsung", 0L);

    @GetMapping("/")
    public List<Product> getProducts() {
        return List.of(IPHONE_11_PRODUCT, SAMSUNG_GALAXY_S20);
    }

    @GetMapping("/branded")
    public List<Product> getProductRequestParam(@RequestParam(required = false) final String brand) {
        if (brand != null) {
            if (brand.equals("iphone")) {
                return List.of(IPHONE_11_PRODUCT);
            } else {
                return List.of(SAMSUNG_GALAXY_S20);
            }
        } else {
            return List.of(IPHONE_11_PRODUCT, SAMSUNG_GALAXY_S20);
        }
    }

    @GetMapping("/{productName}")
    public Product getProductPathVariable(@PathVariable final String productName) {
        if (productName.equalsIgnoreCase("11")) {
            return IPHONE_11_PRODUCT;
        } else {
            return SAMSUNG_GALAXY_S20;
        }
    }

    @PostMapping("/")
    public void createProduct(@RequestBody final Product product) {
        System.out.println(String.format("Pretending to save product %s", product));
    }

    @PutMapping("/{productName}")
    public ResponseEntity<Void> updateProduct(@PathVariable final String productName,
                                              @RequestBody final Product product) {
        System.out.println(String.format("Pretending to update product %s", product));
        System.out.println("Should return 201 Created when created new or 204 No Content when updated");
//        return ResponseEntity.created(URI.create("/products/{productName}")).build();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{productName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchProduct(@RequestBody final Map<String, Object> updates,
                             @PathVariable final String productName) {
        System.out.println(String.format("Pretending to patch product of name %s", productName));
    }

    @DeleteMapping("/{productName}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final String productName) {
        System.out.println(String.format("Pretending to delete product of name %s", productName));
        System.out.println("Should return 200 OK when deleted or 204 No Content when not found");
//        return ResponseEntity.noContent().build();
        return ResponseEntity.ok().build();
    }
}

package com.codecool.shopspring.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/branded")
    public List<Product> getProductRequestParam(@RequestParam(required = false) final String brand) {
        return productService.findAllOfBrand(brand);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable final long id) {
        return productService.findProductById(id);
    }

    @PostMapping("/")
    public void createProduct(@RequestBody final Product product) {
        productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable final Long id,
                                              @RequestBody final Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}

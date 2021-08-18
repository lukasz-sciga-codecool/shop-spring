package com.codecool.shopspring.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAllOfBrand(final String brand) {
        return repository.findByBrand(brand);
    }

    public Optional<Product> findProductById(final Long id) {
        return repository.findById(id);
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(long id, Product product) {
        final var optionalProduct = repository.findById(id);
        final var productToSave = optionalProduct.map(p -> updateProductFields(p, product)).orElse(product);
        return repository.save(productToSave);
    }

    private Product updateProductFields(Product current, Product updates) {
        current.setBrand(updates.getBrand());
        current.setName(updates.getName());
        current.setOrder(updates.getOrder());
        return current;
    }

    public void deleteProduct(long id) {
        repository.deleteById(id);
    }
}

package com.codecool.shopspring.product;

import org.springframework.stereotype.Service;

import java.util.List;

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

    public Product findProductById(final Long id) {
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public void createProduct(Product product) {
        repository.save(product);
    }

    public boolean updateProduct(long id, Product product) {
        final var optionalProduct = repository.findById(id);
        final var productToSave = optionalProduct.map(p -> updateProductFields(p, product)).orElse(product);
        repository.save(productToSave);
        return optionalProduct.isPresent();
    }

    private Product updateProductFields(Product current, Product updates) {
        current.setBrand(updates.getBrand());
        current.setName(updates.getName());
        return current;
    }

    public void deleteProduct(long id) {
        repository.deleteById(id);
    }
}

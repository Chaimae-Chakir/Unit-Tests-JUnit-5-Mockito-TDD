package com.example.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public boolean checkProductIfNull(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.isPresent();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

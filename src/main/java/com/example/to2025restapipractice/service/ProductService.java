package com.example.to2025restapipractice.service;

import com.example.to2025restapipractice.dto.ProductResponse;
import com.example.to2025restapipractice.entity.Product;
import com.example.to2025restapipractice.exception.EntityNotFoundException;
import com.example.to2025restapipractice.exception.IncorrectStatusException;
import com.example.to2025restapipractice.exception.ProductUniqueException;
import com.example.to2025restapipractice.mapper.ProductMapper;
import com.example.to2025restapipractice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts(Double minPrice, Double maxPrice) {
        if (minPrice == null || maxPrice == null) {
            return productRepository.findAll();
        }
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public Product createProduct(Product product) {
        boolean isPresent = productRepository.findByCode(product.getCode()).isPresent();
        if (isPresent) {
            throw new ProductUniqueException("Product already exists");
        }

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public void updateStatus(Long id, String status) {
        List<String> statuses = List.of("AVAILABLE", "OUT_OF_STOCK");
        if (!statuses.contains(status)) {
            throw new IncorrectStatusException("Incorrect status");
        }
        Product product = getProductById(id);
        product.setStatus(status);
        productRepository.save(product);
    }
}

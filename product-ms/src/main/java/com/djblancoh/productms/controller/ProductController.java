package com.djblancoh.productms.controller;

import com.djblancoh.productms.entity.ProductEntity;
import com.djblancoh.productms.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok(productRepository.save(productEntity));
    }


}

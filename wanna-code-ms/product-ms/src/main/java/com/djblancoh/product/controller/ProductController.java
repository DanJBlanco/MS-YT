package com.djblancoh.product.controller;

import com.djblancoh.product.entity.ProductEntity;
import com.djblancoh.product.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    private final SessionRegistry sessionRegistry;

    public ProductController(ProductRepository productRepository, SessionRegistry sessionRegistry) {
        this.productRepository = productRepository;
        this.sessionRegistry = sessionRegistry;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        return ResponseEntity.ok(productRepository.findAll());
//        return ResponseEntity.ok(ProductEntity.builder()
//                        .id(UUID.randomUUID().toString())
//                        .productDescription("productDescrip")
//                        .productName("ProductNAme")
//                        .unitPrice(12.23)
//                .build()
    }

    @GetMapping("/auth")
    public ResponseEntity<?> createProduct() {
        return ResponseEntity.ok(
                ProductEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .productDescription("productDescrip")
                        .productName("ProductNAme")
                        .unitPrice(12.23)
                .build()
        );
    }

    @GetMapping("/session")
    public ResponseEntity<?> getDetailSession() {
        String sessionId = "";
        User userObject = null;

        List<Object> sessions = sessionRegistry.getAllPrincipals();

        for (Object session : sessions) {
            if ( session instanceof User) {
                userObject = (User) session;
            }
            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);

            for (SessionInformation sessionInformation: sessionInformations) {
                sessionId = sessionInformation.getSessionId();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("response", "Hello world");
        response.put("sessionId", sessionId);
        response.put("sessionUser", userObject);



        return ResponseEntity.ok(response);
    }


}
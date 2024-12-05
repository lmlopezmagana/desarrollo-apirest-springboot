package com.example.rest.controller;


import com.example.rest.model.Product;
import com.example.rest.model.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    /*@GetMapping
    public ResponseEntity<List<Product>> getAll(
            @RequestParam(required = false, value = "maxPrice", defaultValue = "-1") double max,
            @RequestParam(required = false, value = "sort", defaultValue = "no") String sortDirection
    ) {

        List<Product> result = productRepository.query(max, sortDirection);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }*/

    @GetMapping
    public ResponseEntity<List<Product>> getAllv2(
            @RequestParam Map<String, String> params
            ) {

        double max = Double.valueOf(params.getOrDefault("maxPrice", "-1"));
        String sortDirection = params.getOrDefault("sort", "no");

        List<Product> result = productRepository.query(max, sortDirection);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }



    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.of(
                productRepository.get(id)
        );
    }



    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productRepository.add(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> edit(
            @RequestBody Product product,
            @PathVariable("id") Long productId) {

        return ResponseEntity.of(productRepository.edit(productId, product));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productRepository.delete(id);
        return ResponseEntity.noContent().build();
    }



}

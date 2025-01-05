package com.openwebinars.rest.controller;


import com.openwebinars.rest.model.Product;
import com.openwebinars.rest.model.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@Log
//@RestController
//@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductControllerOld {

    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(
            @RequestParam(required = false, value = "maxPrice", defaultValue = "-1") double max,
            @RequestParam(required = false, value = "sort", defaultValue = "no") String sortDirection
    ) {

        List<Product> result = productRepository.query(max, sortDirection);

        if (result.isEmpty())
            //return ResponseEntity.notFound().build();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay productos con esos requisitos de búsqueda");

        return ResponseEntity.ok(result);

    }

    /*@GetMapping
    public ResponseEntity<List<Product>> getAllv2(
            @RequestParam Map<String, String> params
            ) {

        double max = Double.valueOf(params.getOrDefault("maxPrice", "-1"));
        String sortDirection = params.getOrDefault("sort", "no");

        List<Product> result = productRepository.query(max, sortDirection);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(result);

    }*/



    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        /*return ResponseEntity.of(
                productRepository.get(id)
        );*/
        return productRepository.get(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    var exception =  new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay producto con ese ID: %d".formatted(id));
                    exception.setTitle("Producto no encontrado");
                    exception.setType(URI.create("https://www.openwebinars.net/errors/product-not-found"));
                    throw exception;
                });
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

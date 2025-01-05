package com.openwebinars.rest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    /*@GetMapping("/producto")
    public Producto obtenerProducto() {
        return new Producto(1L, "Nombre del producto");
    }

    @PostMapping("/producto")
    public Producto nuevoProducto(@RequestBody Producto producto) {
        return producto;
    }

    record Producto(Long id, String nombre) {

    }*/

}


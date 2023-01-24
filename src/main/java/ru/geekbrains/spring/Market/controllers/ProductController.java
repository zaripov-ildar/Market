package ru.geekbrains.spring.Market.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.Market.entities.Product;
import ru.geekbrains.spring.Market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.Market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product (id:%d) not found", id)));

    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable long id) {
        productService.deleteById(id);
    }
}



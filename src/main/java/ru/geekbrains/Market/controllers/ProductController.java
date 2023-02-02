package ru.geekbrains.Market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Market.entities.ProductEntity;
import ru.geekbrains.Market.exceptions.ResourceNotFoundException;
import ru.geekbrains.Market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductEntity> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductEntity findProductById(@PathVariable long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Product (id:%d) not found", id)));

    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable long id) {
        productService.deleteById(id);
    }
}



package ru.geekbrains.spring.Market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.Market.dtos.Cart;
import ru.geekbrains.spring.Market.entities.Product;
import ru.geekbrains.spring.Market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final Cart cart;

    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable long id) {
        return productService.findById(id).orElseThrow(()->new RuntimeException(String.format("Product #%d not found", id)));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable long id) {
        productService.deleteById(id);
    }

    @PostMapping("/add/{id}")
    public Cart addToCart(@PathVariable long id){
        cart.addProduct(productService.findById(id).orElseThrow(()->new RuntimeException(String.format("Product #%d not found", id))));
        return cart;
    }
}

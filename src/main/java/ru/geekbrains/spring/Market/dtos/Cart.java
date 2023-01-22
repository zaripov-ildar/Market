package ru.geekbrains.spring.Market.dtos;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.Market.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private final List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}

package ru.geekbrains.spring.Market.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.Market.aop.Timer;
import ru.geekbrains.spring.Market.dtos.Cart;
import ru.geekbrains.spring.Market.entities.Product;
import ru.geekbrains.spring.Market.exceptions.ResourceNotFoundException;


@Service
@RequiredArgsConstructor
@Timer
public class CartService {
    private Cart tempCart;

    private final ProductService productService;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void changeAmount(Long productId, int delta) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException(String.format("Can't find product (id:%d) to put to the cart", productId)));
        tempCart.changeAmount(product, delta);
    }

    public void delete(Long id) {
        tempCart.delete(id);
    }

    public void clear() {
        tempCart.clear();
    }
}



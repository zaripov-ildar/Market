package ru.geekbrains.spring.Market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.Market.aop.Timer;
import ru.geekbrains.spring.Market.dtos.Cart;
import ru.geekbrains.spring.Market.dtos.CartDto;
import ru.geekbrains.spring.Market.services.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;


    @GetMapping("/changeAmount")
    public void changeAmount(@RequestParam("productId") Long id, @RequestParam("delta") int delta) {
        cartService.changeAmount(id, delta);
    }
    @Timer
    @GetMapping
    public CartDto getCurrentCart() {
        Cart cart = cartService.getCurrentCart();
        return new CartDto(cart.getCartItems(), cart.getTotalPrice());
    }

    @GetMapping("/delete/{id}")
    public void deleteItemById(@PathVariable Long id) {
        cartService.delete(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }
}

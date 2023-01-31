package ru.geekbrains.Market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.Market.dtos.OrderDto;
import ru.geekbrains.Market.entities.Order;
import ru.geekbrains.Market.services.OrderService;
import ru.geekbrains.Market.utils.Cart;
import ru.geekbrains.Market.dtos.CartDto;
import ru.geekbrains.Market.aop.Timer;
import ru.geekbrains.Market.services.CartService;
import ru.geekbrains.Market.converters.Converter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;
    private final Converter converter;


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

    @GetMapping("/order")
    public OrderDto makeOrder() {
        Order order = converter.convert(cartService.getCurrentCart(), 1L);
        return converter.convert(orderService.save(order));
    }
}

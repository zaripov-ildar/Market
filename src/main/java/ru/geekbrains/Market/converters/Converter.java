package ru.geekbrains.Market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.Market.dtos.OrderDto;
import ru.geekbrains.Market.entities.Order;
import ru.geekbrains.Market.entities.OrderItem;
import ru.geekbrains.Market.entities.Product;
import ru.geekbrains.Market.entities.User;
import ru.geekbrains.Market.exceptions.ResourceNotFoundException;
import ru.geekbrains.Market.services.ProductService;
import ru.geekbrains.Market.services.UserService;
import ru.geekbrains.Market.utils.Cart;
import ru.geekbrains.Market.utils.CartItem;

@RequiredArgsConstructor
@Component
public class Converter {
    private final ProductService productService;
    private final UserService userService;

    public OrderDto convert(Order order) {
        return OrderDto.builder()
                .username(order.getUser().getUsername())
                .items(order.getItems().stream().map(this::convert).toList())
                .id(order.getId())
                .build();
    }

    private OrderItem convert(CartItem cartItem) {
        Product product = productService.findById(cartItem.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Can't find product #" + cartItem.getProductId()));
        return OrderItem.builder()
                .amount(cartItem.getQuantity())
                .product(product)
                .price(cartItem.getPrice())
                .build();
    }

    private CartItem convert(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        return new CartItem(
                product.getId(), product.getTitle(), orderItem.getAmount(), product.getPrice(), orderItem.getPrice()
        );
    }

    public Order convert(Cart currentCart, Long l) {
        User user = userService.findUserById(l).orElseThrow(() -> new ResourceNotFoundException("Can't find user #" + l));
        return Order.builder()
                .user(user)
                .items(currentCart.getCartItems().stream().map(this::convert).toList())
                .totalPrice(currentCart.getTotalPrice())
                .build();

    }
}

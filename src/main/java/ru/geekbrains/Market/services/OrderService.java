package ru.geekbrains.Market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.Market.converters.Converter;
import ru.geekbrains.Market.entities.Order;
import ru.geekbrains.Market.entities.User;
import ru.geekbrains.Market.exceptions.ResourceNotFoundException;
import ru.geekbrains.Market.repositories.OrderRepository;
import ru.geekbrains.Market.utils.Cart;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;


    public Order save(Order order) {
        return orderRepository.save(order);
    }
}

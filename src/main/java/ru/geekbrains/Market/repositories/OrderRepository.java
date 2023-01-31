package ru.geekbrains.Market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.Market.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

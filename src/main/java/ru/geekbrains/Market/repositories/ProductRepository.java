package ru.geekbrains.Market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.Market.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

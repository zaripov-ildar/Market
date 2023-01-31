package ru.geekbrains.Market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.Market.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}

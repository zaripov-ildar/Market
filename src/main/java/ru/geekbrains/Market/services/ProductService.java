package ru.geekbrains.Market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.Market.entities.ProductEntity;
import ru.geekbrains.Market.repositories.ProductRepository;
import ru.geekbrains.Market.soap.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> findById(long id) {
        return productRepository.findById(id);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public static final Function<ProductEntity, Product> functionEntityToSoap =
            productEntity -> {
                Product product = new Product();
                product.setId(productEntity.getId());
                product.setPrice(productEntity.getPrice());
                product.setTitle(productEntity.getTitle());
                return product;
            };


    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .map(functionEntityToSoap)
                .toList();
    }
}

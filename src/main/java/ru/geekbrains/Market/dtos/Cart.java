package ru.geekbrains.Market.dtos;

import lombok.Data;
import ru.geekbrains.Market.entities.ProductEntity;

import java.util.*;

@Data
public class Cart {

    private Map<Long, CartItem> map;
    private int totalPrice;

    public Cart() {
        map = new HashMap<>();
    }

    public void changeAmount(ProductEntity productEntity, int i) {
        long id = productEntity.getId();
        addToCart(productEntity, i, id);
        deleteIfAmountLessOrEqualZero(id);
        recalculate();
    }

    private void addToCart(ProductEntity productEntity, int i, long id) {
        if (!map.containsKey(id)) {
            map.put(id, new CartItem(id, productEntity.getTitle(), 1, productEntity.getPrice(), productEntity.getPrice()));
        } else {
            map.get(id).changeAmount(i);
        }
    }

    private void deleteIfAmountLessOrEqualZero(long id) {
        if (map.get(id).getQuantity() <= 0) {
            map.remove(id);
        }
    }

    private void recalculate() {
        totalPrice = 0;
        map.values().forEach(i -> totalPrice += i.getPrice());
    }

    public List<CartItem> getCartItems() {
        return map.values().stream().toList();
    }

    public void delete(Long id) {
        map.remove(id);
    }

    public void clear() {
        map.clear();
    }
}

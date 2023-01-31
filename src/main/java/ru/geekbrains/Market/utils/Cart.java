package ru.geekbrains.Market.utils;

import lombok.Data;
import ru.geekbrains.Market.entities.Product;

import java.util.*;

@Data
public class Cart {

    private Map<Long, CartItem> map;
    private int totalPrice;

    public Cart() {
        map = new HashMap<>();
    }

    public void changeAmount(Product product, int i) {
        long id = product.getId();
        addToCart(product, i, id);
        deleteIfAmountLessOrEqualZero(id);
        recalculate();
    }

    private void addToCart(Product product, int i, long id) {
        if (!map.containsKey(id)) {
            map.put(id, new CartItem(id, product.getTitle(), 1, product.getPrice(), product.getPrice()));
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
        recalculate();
    }

    public void clear() {
        totalPrice = 0;
        map.clear();
    }
}

package ru.geekbrains.Market.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public void changeAmount(int amount) {
        quantity += amount;
        recalculate();
    }

    private void recalculate() {
        price = pricePerProduct * quantity;
    }
}

package ru.geekbrains.Market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.Market.utils.CartItem;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private List<CartItem> items;
    private int totalPrice;
}

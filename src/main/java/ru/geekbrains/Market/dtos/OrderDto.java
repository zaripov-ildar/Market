package ru.geekbrains.Market.dtos;

import lombok.Builder;
import lombok.Data;
import ru.geekbrains.Market.utils.CartItem;

import java.util.List;

@Data
@Builder
public class OrderDto {
    private Long id;
    private String username;
    private List<CartItem> items;
}

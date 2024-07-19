package com.shipshoe.productservice.dto;

import com.shipshoe.productservice.entity.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemResponseList {
    private Long itemId;
    private String name;
    private int price;

    public ItemResponseList(Item item) {
        this.itemId = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
    }
}

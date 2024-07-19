package com.shipshoe.productservice.dto;

import com.shipshoe.productservice.entity.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ItemResponseDto {

    private Long itemId;
    private String name;
    private int price;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public ItemResponseDto(Item item) {
        this.itemId = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stock = item.getStock();
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
    }
}

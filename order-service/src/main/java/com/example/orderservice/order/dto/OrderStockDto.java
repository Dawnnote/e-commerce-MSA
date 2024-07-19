package com.example.orderservice.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStockDto {

    private Long itemId;
    private int quantity;

    public OrderStockDto(OrderRequest request) {
        this.itemId = request.getItemId();
        this.quantity = request.getCount();
    }
}

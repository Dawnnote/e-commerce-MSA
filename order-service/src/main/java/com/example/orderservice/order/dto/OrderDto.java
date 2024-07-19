package com.example.orderservice.order.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {
    private Long itemId;
    private int count;

    public OrderDto(OrderRequest request) {
        this.itemId = request.getItemId();
        this.count = request.getCount();
    }
}


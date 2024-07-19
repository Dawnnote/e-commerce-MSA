package com.example.orderservice.order_line.dto;

import com.example.orderservice.order_line.entity.OrderLine;
import lombok.Data;

@Data
public class OrderLineDto {
    private Long id;
    private String itemName;
    private int quantity;
    private int price;

    public OrderLineDto(OrderLine orderLine) {
        this.id = orderLine.getId();
        this.itemName = orderLine.getItemName();
        this.quantity = orderLine.getOrderCount();
        this.price = orderLine.getOrderPrice();
    }
}

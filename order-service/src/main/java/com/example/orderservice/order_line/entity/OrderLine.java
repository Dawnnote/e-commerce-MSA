package com.example.orderservice.order_line.entity;

import com.example.orderservice.order.entity.Order;
import com.example.orderservice.vo.ProductResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class OrderLine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long id;

//    @Column(nullable = false)
//    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int orderCount;

    protected OrderLine() {}

    public static OrderLine createOrderLine(ProductResponseDto itemDto, int orderCount) {
        OrderLine orderLine = new OrderLine();

        orderLine.itemName = itemDto.getName();
        orderLine.orderPrice = itemDto.getPrice();
        orderLine.orderCount = orderCount;

        return orderLine;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

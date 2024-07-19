package com.example.orderservice.order.dto;

import com.example.orderservice.delivery.dto.DeliveryDto;
import com.example.orderservice.order.entity.Order;
import com.example.orderservice.order.entity.OrderStatus;
import com.example.orderservice.order_line.dto.OrderLineDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private Long userId;
    private DeliveryDto delivery;
    private List<OrderLineDto> orderLines;
    private OrderStatus status;
    private LocalDateTime orderDate;


    public OrderResponse(Order order) {
        this.userId = order.getUserId();
        delivery = new DeliveryDto(order.getDelivery());
        this.orderLines = order.getOrderLines().stream()
                .map(OrderLineDto::new)
                .toList();
        this.status = order.getStatus();
        this.orderDate = order.getCreatedAt();
    }
}

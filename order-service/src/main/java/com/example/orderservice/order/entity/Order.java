package com.example.orderservice.order.entity;

import com.example.orderservice.BaseEntity;
import com.example.orderservice.delivery.entity.Delivery;
import com.example.orderservice.order_line.entity.OrderLine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@ToString
public class Order extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order() {}

    public static Order createOrder(Long userId, Delivery delivery, OrderLine... orderLines) {
        Order order = new Order();

        order.userId = userId;
        order.delivery = delivery;
        for (OrderLine orderLine : orderLines) {
            order.addOrderLine(orderLine);
        }
        order.status = OrderStatus.ORDER;
        return order;
    }

    private void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
        orderLine.setOrder(this);
    }
}

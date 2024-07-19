package com.example.orderservice.delivery.entity;

import com.example.orderservice.Address;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    protected Delivery(){}

    public Delivery(Address address) {
        this.address = address;
        this.status = DeliveryStatus.READY;
    }
}

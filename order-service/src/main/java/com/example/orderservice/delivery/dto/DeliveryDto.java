package com.example.orderservice.delivery.dto;

import com.example.orderservice.Address;
import com.example.orderservice.delivery.entity.Delivery;
import com.example.orderservice.delivery.entity.DeliveryStatus;
import lombok.Data;

@Data
public class DeliveryDto {

    private Long id;
    private Address address;
    private DeliveryStatus status;

    public DeliveryDto() {
    }

    public DeliveryDto(Delivery delivery) {
        this.id = delivery.getId();
        this.address = delivery.getAddress();
        this.status = delivery.getStatus();
    }
}

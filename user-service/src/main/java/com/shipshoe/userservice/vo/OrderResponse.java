package com.shipshoe.userservice.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private Long userId;
    private LocalDateTime orderDate;
}

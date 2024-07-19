package com.example.orderservice.vo;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String name;
    private int price;
    private int stock;
}

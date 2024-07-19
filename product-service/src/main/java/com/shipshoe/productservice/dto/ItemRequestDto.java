package com.shipshoe.productservice.dto;

import lombok.Data;

@Data
public class ItemRequestDto {

    private String name;
    private int price;
    private int stock;
}

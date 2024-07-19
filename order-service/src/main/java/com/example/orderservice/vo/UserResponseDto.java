package com.example.orderservice.vo;

import com.example.orderservice.Address;
import lombok.Data;

@Data
public class UserResponseDto {
    private String email;
    private String name;
    private Address address;
    private String phone;
}

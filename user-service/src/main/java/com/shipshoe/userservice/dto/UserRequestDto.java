package com.shipshoe.userservice.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;

    private String state;
    private String city;
    private String zipcode;
    private String street;
}

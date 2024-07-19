package com.shipshoe.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFormDto {
    private String password;
    private String phone;
    private String state;
    private String city;
    private String zipcode;
    private String street;



//    public UserFormDto(User user) {
//        this.password = user.getPassword();
//        this.phone = user.getPhone();
//        this.state = user.getAddress().getState();
//        this.city = user.getAddress().getCity();
//        this.zipcode = user.getAddress().getZipcode();
//        this.street = user.getAddress().getStreet();
//    }
}



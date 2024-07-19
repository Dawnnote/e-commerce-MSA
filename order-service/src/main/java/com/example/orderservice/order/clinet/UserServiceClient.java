package com.example.orderservice.order.clinet;

import com.example.orderservice.vo.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service")
public interface UserServiceClient {

    @GetMapping("/api/users/{userId}")
    UserResponseDto getUser(@PathVariable Long userId);
}

package com.example.orderservice.order.clinet;

import com.example.orderservice.vo.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/product-service/api/items/{itemId}")
    ProductResponseDto getItem(@PathVariable Long itemId);

}

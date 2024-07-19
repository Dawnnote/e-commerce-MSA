package com.example.orderservice.order.api;


import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.order.dto.OrderRequest;
import com.example.orderservice.order.dto.OrderResponse;
import com.example.orderservice.order.entity.Order;
import com.example.orderservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final Environment env;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s",
                env.getProperty("local.server.port"));
    }

    // 주문 생성
    @PostMapping("/api/orders")
    public OrderResponse createOrder(@RequestHeader("User-Id") Long userId,
                                     @RequestBody OrderRequest request) {

        return orderService.createOrder(request, userId);
    }

    // 주문 조회
    @GetMapping("/api/orders")
    public List<OrderResponse> getOrder(@RequestHeader("User-Id") Long userId) {
        return orderService.getOrders(userId);
    }
}

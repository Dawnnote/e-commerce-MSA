package com.example.orderservice.order.service;


import com.example.orderservice.delivery.entity.Delivery;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.order.clinet.ProductServiceClient;
import com.example.orderservice.order.clinet.UserServiceClient;
import com.example.orderservice.order.dto.OrderRequest;
import com.example.orderservice.order.dto.OrderResponse;
import com.example.orderservice.order.dto.OrderStockDto;
import com.example.orderservice.order.entity.Order;
import com.example.orderservice.order.repository.OrderRepository;
import com.example.orderservice.order_line.entity.OrderLine;
import com.example.orderservice.vo.ProductResponseDto;
import com.example.orderservice.vo.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserServiceClient userServiceClient;
    private final ProductServiceClient productServiceClient;
    private final KafkaProducer kafkaProducer;

    // 주문 생성
    @Transactional
    public OrderResponse createOrder(OrderRequest request, Long userId) {

        Long itemId = request.getItemId();

        UserResponseDto userDto = userServiceClient.getUser(userId);
        ProductResponseDto itemDto = productServiceClient.getItem(itemId);

        if (itemDto.getStock() < request.getCount()) {
            throw new IllegalArgumentException("재고 부족");
        }

        // 주문 생성
        OrderLine orderLine = OrderLine.createOrderLine(itemDto, request.getCount());
        Delivery delivery = new Delivery(userDto.getAddress());
        Order order = Order.createOrder(userId, delivery, orderLine);

        // 재고
        OrderStockDto orderStockDto = new OrderStockDto(request);
        /* send this order to the kafka */
        kafkaProducer.send("product-topic", orderStockDto);

        orderRepository.save(order);
        return new OrderResponse(order);
    }

    // 주문 하나만 조회
    public OrderResponse getOrder(Long orderId) {
        Order findOrder = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
        return new OrderResponse(findOrder);
    }

    // 주문 내역 검색해오기
    public List<OrderResponse> getOrders(Long userId) {
        List<Order> orderList = orderRepository.findByUserId(userId);
        return orderList.stream().map(OrderResponse::new).toList();
    }
}

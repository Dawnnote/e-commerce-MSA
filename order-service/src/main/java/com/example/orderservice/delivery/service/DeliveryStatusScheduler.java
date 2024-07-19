//package com.example.orderservice.delivery.service;
//
//import com.example.orderservice.order.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class DeliveryStatusScheduler {
//
//    private final OrderService orderService;
//
//    // 매일 23시 59분 배송 상태 업데이트
//    @Scheduled(cron = "0 59 23 * * *")
//    public void updateDeliveryStatus() {
//        orderService.updateOrderDeliveryStatus();
//        log.info("Schedule Counter");
//    }
//}

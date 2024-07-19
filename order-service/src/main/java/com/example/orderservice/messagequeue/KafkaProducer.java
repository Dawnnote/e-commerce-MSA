package com.example.orderservice.messagequeue;

import com.example.orderservice.order.dto.OrderDto;
import com.example.orderservice.order.dto.OrderResponse;
import com.example.orderservice.order.dto.OrderStockDto;
import com.example.orderservice.order_line.dto.OrderLineDto;
import com.example.orderservice.order_line.entity.OrderLine;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderStockDto send(String topic, OrderStockDto OrderStockDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(OrderStockDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer send data from the Order microservices: {}", OrderStockDto);

        return OrderStockDto;
    }
}

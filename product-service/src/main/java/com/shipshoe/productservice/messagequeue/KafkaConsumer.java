package com.shipshoe.productservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shipshoe.productservice.repository.ItemRepository;
import com.shipshoe.productservice.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class KafkaConsumer {

    ItemRepository itemRepository;
    ItemService itemService;

    @Autowired
    public KafkaConsumer(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }


    @KafkaListener(topics = "product-topic")
    public void updateQty(String kafkaMessage) {
        log.info("Kafka Message: -> {}", kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        itemService.decrementStock(Long.valueOf(map.get("itemId").toString()), (Integer) map.get("quantity"));


//        Item item = itemRepository.findById(Long.valueOf(map.get("itemId").toString())).orElseThrow(IllegalArgumentException::new);
//        item.decrementStock((Integer) map.get("count"));
//        itemRepository.save(item);
    }
}

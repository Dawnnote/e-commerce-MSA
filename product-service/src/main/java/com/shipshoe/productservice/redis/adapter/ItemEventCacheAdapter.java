package com.shipshoe.productservice.redis.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shipshoe.productservice.dto.ItemResponseDto;
import com.shipshoe.productservice.redis.CustomObjectMapper;
import com.shipshoe.productservice.redis.port.ItemEventCachePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
@Slf4j
public class ItemEventCacheAdapter implements ItemEventCachePort {

    private static final String KEY_PREFIX = "item_event_cache.v1:";
    private static final Long EXPIRE_SECONDS = 60 * 2L; // 2분
    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private final RedisTemplate<String, String> redisTemplate;


    @Override
    public void set(ItemResponseDto responseDto) {
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(responseDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        redisTemplate.opsForValue().set(
                this.generateCacheKey(responseDto.getItemId()),
                jsonString,
                Duration.ofSeconds(EXPIRE_SECONDS)
        );
    }

    @Override
    public ItemResponseDto get(Long itemId) {
        String jsonString = redisTemplate.opsForValue().get(this.generateCacheKey(itemId));
        if (jsonString == null) return null;
        try {
            ItemResponseDto itemResponseDto = objectMapper.readValue(jsonString, ItemResponseDto.class);

            // Redis에서 stock 값 가져오기

            getRedisStock(itemId, itemResponseDto);
//            String stockValue = redisTemplate.opsForValue().get(this.generateCacheKey(itemId));
//            if (stockValue != null) {
//                itemResponseDto.setStock(Integer.parseInt(stockValue));
//            }

            return itemResponseDto;

//            return objectMapper.readValue(jsonString, ItemResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void incrementProductStock(String itemId, int stock) {
//        redisTemplate2.opsForValue().increment(ITEM_STOCK_KEY + itemId, stock);
    }

    @Override
    public void decrementProductStock(Long itemId, int quantity) {

        String itemJson = redisTemplate.opsForValue().get(this.generateCacheKey(itemId));

        try {
            JsonNode jsonNode = objectMapper.readTree(itemJson);
            int stock = jsonNode.get("stock").asInt();
            log.info("stock = {}", stock);

            if (stock == 0 || stock < quantity) {
                log.error("Not enough stock for item ID: {}", itemId);
                return;
            }

            ObjectNode ob = ((ObjectNode) jsonNode).put("stock", stock - quantity);
            redisTemplate.opsForValue().set(this.generateCacheKey(itemId), String.valueOf(ob));


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getRedisStock(Long itemId, ItemResponseDto itemResponseDto) {
        String itemJson = redisTemplate.opsForValue().get(this.generateCacheKey(itemId));
        try {
            JsonNode jsonNode = objectMapper.readTree(itemJson);
            int stock = jsonNode.get("stock").asInt();

            itemResponseDto.setStock(stock);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateCacheKey(Long itemId) {
        return KEY_PREFIX + itemId;
    }

}

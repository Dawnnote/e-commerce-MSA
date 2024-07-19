package com.shipshoe.productservice.redis.port;

import com.shipshoe.productservice.dto.ItemResponseDto;

public interface ItemEventCachePort {

    void set(ItemResponseDto responseDto);

    ItemResponseDto get(Long itemId);

    void incrementProductStock(String itemId, int stock);

    void decrementProductStock(Long itemId, int quantity);

    void getRedisStock(Long itemId, ItemResponseDto itemResponseDto);

}

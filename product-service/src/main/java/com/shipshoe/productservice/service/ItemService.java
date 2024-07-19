package com.shipshoe.productservice.service;

import com.shipshoe.productservice.dto.ItemRequestDto;
import com.shipshoe.productservice.dto.ItemResponseDto;
import com.shipshoe.productservice.dto.ItemResponseList;
import com.shipshoe.productservice.entity.Item;
import com.shipshoe.productservice.redis.port.ItemEventCachePort;
import com.shipshoe.productservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemEventCachePort itemEventCachePort;


    public List<ItemResponseList> findItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(ItemResponseList::new)
                .toList();
    }

    public ItemResponseDto findOne(Long itemId) {
        return this.getItemEventById(itemId);
    }

    @Transactional
    public ItemResponseDto create(ItemRequestDto request) {
        Item item = new Item();
        item.createItem(request);
        Long id = itemRepository.save(item).getId();
        Item findItem = itemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new ItemResponseDto(findItem);
    }

    @Transactional
    public void decrementStock(Long itemId, int quantity) {
        itemEventCachePort.decrementProductStock(itemId, quantity);
    }

    // 캐시 작업
    @Transactional
    public ItemResponseDto getItemEventById(Long itemId) {
        ItemResponseDto itemCache = itemEventCachePort.get(itemId);

        if (itemCache != null) {
            return itemCache;
        } else {
            Item item = itemRepository.findById(itemId).orElse(null);
            if (item == null) {
                throw new RuntimeException("존재 하지 않는 아이템 입니다");
            }
            ItemResponseDto responseDto = new ItemResponseDto(item);
            itemEventCachePort.set(responseDto);
            return responseDto;
        }
    }
}

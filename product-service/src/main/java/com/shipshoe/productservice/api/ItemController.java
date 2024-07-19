package com.shipshoe.productservice.api;

import com.shipshoe.productservice.dto.ItemRequestDto;
import com.shipshoe.productservice.dto.ItemResponseDto;
import com.shipshoe.productservice.dto.ItemResponseList;
import com.shipshoe.productservice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s",
                env.getProperty("local.server.port"));
    }


    @GetMapping("/api/items")
    public List<ItemResponseList> getItemList() {
        return itemService.findItems();
    }

    @GetMapping("/api/items/{itemId}")
    public ItemResponseDto getItem(@PathVariable("itemId") Long itemId) {
        return itemService.findOne(itemId);
    }

    @PutMapping("/api/items/{itemId}")
    public void decrementStock(@PathVariable("itemId") Long itemId, @RequestParam int quantity) {
        itemService.decrementStock(itemId, quantity);
    }

    @PostMapping("/api/items")
    public ItemResponseDto create(@RequestBody ItemRequestDto request) {
        return itemService.create(request);
    }


}

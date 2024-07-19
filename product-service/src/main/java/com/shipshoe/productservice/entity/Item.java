package com.shipshoe.productservice.entity;

import com.shipshoe.productservice.BaseEntity;
import com.shipshoe.productservice.dto.ItemRequestDto;
import com.shipshoe.productservice.dto.ItemResponseDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

@Entity
@Getter
public class Item extends BaseEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int stock;

    public void createItem(ItemRequestDto request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.stock = request.getStock();
    }



    // 재고 증가
    public void addStock(int orderCount) {
        this.stock += orderCount;
    }

    // 재고 감소
    public void decrementStock(int quantity) {
        this.stock -= quantity;
    }

    public void updateItem(ItemResponseDto responseDto, Integer count) {
        this.id = responseDto.getItemId();
        this.name = responseDto.getName();
        this.price = responseDto.getPrice();
        this.stock = responseDto.getStock() - count;
    }
}

package com.feign.demo.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private String orderId;
    private String status;
    private String customer;
    private List<Item> items;
    private double totalAmount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private String productId;
        private int quantity;
    }
}
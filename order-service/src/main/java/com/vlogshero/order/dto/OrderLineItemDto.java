package com.vlogshero.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDto {
    private Long id;
    private String skuCode;
    private double price;
    private int qty;
}

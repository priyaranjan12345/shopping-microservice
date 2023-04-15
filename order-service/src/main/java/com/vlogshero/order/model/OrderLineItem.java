package com.vlogshero.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OrderLineItemTable")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String skuCode;
    private double price;
    private int qty;
}

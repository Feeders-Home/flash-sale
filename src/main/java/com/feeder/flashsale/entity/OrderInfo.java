package com.feeder.flashsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderInfo {
    @Id
    private Long id;
    private Long customerId;
    private Long goodsId;
    private Integer quantity;
    private BigDecimal totalPrice;
}

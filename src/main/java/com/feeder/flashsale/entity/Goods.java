package com.feeder.flashsale.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Goods {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal flash_sale_price;
    private Integer quantity;
    //不要用缩写desc，这里容易误认成降序排序
    private String description;

    public Goods(Long id) {
        this.id = id;
    }
}

package com.feeder.flashsale.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Goods {

    @Id
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

package com.feeder.flashsale.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Repository;

@Data
@Accessors(chain = true)
@Repository
public class Goods {

    private String id;
    private String name;
    /**
     * 库存数量
     */
    private Integer quantity;
    /**
     * 描述
     */
    private String desc;

}

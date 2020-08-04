package com.feeder.flashsale.beans;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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

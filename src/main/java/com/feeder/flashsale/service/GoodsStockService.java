package com.feeder.flashsale.service;

public interface GoodsStockService {

    /**
     * 先查找缓存，查不到再查找库存，并更新到cache中
     * @param goodsId 商品id
     * @return 库存量
     */
    Integer find(int goodsId);

    /**
     * 减商品库存，并把库存更新到cache中
     * @param goodsId
     * @param count
     * @return
     */
    boolean reduceStock(int goodsId, int count);

}

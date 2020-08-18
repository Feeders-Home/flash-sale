package com.feeder.flashsale.service.goodsStock;

public class ReadStockOperator extends StockOperator {
    public ReadStockOperator(int goodsId) {
        super(goodsId);
    }

    @Override
    void doProcess() {

        // 1. 读缓存
        // 2. 如果缓存中不存在，读数据库，并更新到缓存中
    }
}

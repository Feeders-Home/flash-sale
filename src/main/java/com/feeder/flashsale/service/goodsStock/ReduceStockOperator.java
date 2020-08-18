package com.feeder.flashsale.service.goodsStock;

public class ReduceStockOperator extends StockOperator{
    private final int count;

    public ReduceStockOperator(int goodsId, int count) {
        super(goodsId);
        this.count = count;
    }

    @Override
    void doProcess() {
        //1. 删除缓存
        //2. 更新数据库
    }
}

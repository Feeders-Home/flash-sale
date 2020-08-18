package com.feeder.flashsale.service.impl;


import com.feeder.flashsale.service.GoodsStockService;
import com.feeder.flashsale.service.goodsStock.AsyncStockHandler;
import com.feeder.flashsale.service.goodsStock.ReadStockOperator;
import com.feeder.flashsale.service.goodsStock.ReduceStockOperator;

public class GoodsStockServiceImpl implements GoodsStockService {

    private AsyncStockHandler handler;

    public GoodsStockServiceImpl(AsyncStockHandler handler) {
        this.handler = handler;
    }

    @Override
    public Integer find(int goodsId) {
        // 读缓存，有值就返回结果

        ReadStockOperator readStockOperator = new ReadStockOperator(goodsId);
        handler.add(readStockOperator);

        try {
            readStockOperator.getLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return readStockOperator.getResult();
    }

    @Override
    public boolean reduceStock(int goodsId, int count) {
        ReduceStockOperator  operator = new ReduceStockOperator(goodsId, count);
        handler.add(operator);

        try {
            operator.getLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return operator.getResult() == 1;
    }
}

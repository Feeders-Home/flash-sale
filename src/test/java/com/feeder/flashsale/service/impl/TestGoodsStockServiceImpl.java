package com.feeder.flashsale.service.impl;

import com.feeder.flashsale.service.GoodsStockService;
import com.feeder.flashsale.service.goodsStock.AsyncStockHandler;

import org.junit.Test;

public class TestGoodsStockServiceImpl {

    private GoodsStockService goodsStockService;

    @Test
    public void test() {
        goodsStockService = new GoodsStockServiceImpl(new AsyncStockHandler());

        while (true) {
            goodsStockService.find(100);
            goodsStockService.reduceStock(100,1);
        }
    }
}

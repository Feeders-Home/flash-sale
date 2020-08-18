package com.feeder.flashsale.service.goodsStock;

import java.util.concurrent.CountDownLatch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public abstract class StockOperator {
    @Getter
    private int goodsId;
    @Getter
    @Setter
    private CountDownLatch latch = new CountDownLatch(1);
    @Getter
    @Setter
    private int result;

    public StockOperator(int goodsId) {
        this.goodsId = goodsId;
    }

    public void process() {
        try {
            doProcess();
        } finally {
            getLatch().countDown();
        }
    }

    abstract void doProcess();
}
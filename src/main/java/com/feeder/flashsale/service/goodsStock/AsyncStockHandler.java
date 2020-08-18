package com.feeder.flashsale.service.goodsStock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class AsyncStockHandler {

    private static final int NUM = 20;
    private final ExecutorService[] executorServices;

    public AsyncStockHandler() {
        executorServices = Stream.generate(Executors::newSingleThreadExecutor)
            .limit(NUM).toArray(ExecutorService[]::new);
    }

    public void add(StockOperator operator) {
        int index = indexOfQueues(operator.getGoodsId());

        executorServices[index].submit(operator::process);
    }

    private int indexOfQueues(int key) {
        return key % NUM;
    }
}

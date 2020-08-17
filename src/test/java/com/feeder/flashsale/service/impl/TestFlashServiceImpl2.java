package com.feeder.flashsale.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import com.feeder.flashsale.dao.GoodsDao;
import com.feeder.flashsale.dao.OrderInfoDao;
import com.feeder.flashsale.entity.Goods;
import com.feeder.flashsale.entity.OrderInfo;
import com.feeder.flashsale.service.FlashSaleService;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TestFlashServiceImpl2 {

    @Autowired
    GoodsDao goodsDao;
    @Autowired
    OrderInfoDao orderInfoDao;
    @Autowired
    FlashSaleService flashSaleService;

    long goodsId = 2;
    int quantity = 10;

    @Before
    public void setup() {
        goodsDao.insert(new Goods().setId(goodsId).setQuantity(quantity));
    }

    @After
    public void teardown() {
        goodsDao.deleteByPrimaryKey(new Goods().setId(goodsId));
        orderInfoDao.delete(new OrderInfo().setGoodsId(goodsId));
    }

    @Test
    public void testConcurrentAddOrder() throws InterruptedException {
        long customerId = 2;
        int buy_quantity = 1;
        int threadN = 20;
        CountDownLatch latch = new CountDownLatch(threadN);

        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.rangeClosed(1, threadN).forEach((i) ->
                executorService.execute(() -> {
                    try {
                        flashSaleService.addOrder(goodsId, buy_quantity, customerId);
                    } finally {
                        latch.countDown();
                    }
                })
        );

        latch.await();

        Assert.assertEquals(this.quantity, orderInfoDao.selectCount(new OrderInfo().setGoodsId(goodsId)));
    }
}

package com.feeder.flashsale.service.impl;

import java.math.BigDecimal;

import com.feeder.flashsale.dao.GoodsDao;
import com.feeder.flashsale.dao.OrderInfoDao;
import com.feeder.flashsale.entity.Goods;
import com.feeder.flashsale.entity.OrderInfo;
import com.feeder.flashsale.exception.StockException;
import com.feeder.flashsale.service.FlashSaleService;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestFlashSaleServiceImpl {

    OrderInfoDao orderInfoDao;
    GoodsDao goodsDao;
    FlashSaleService flashSaleService;
    @Before
    public void setup() {

        orderInfoDao = mock(OrderInfoDao.class );
        goodsDao = mock(GoodsDao.class );

        flashSaleService = new FlashSaleServiceImpl(orderInfoDao, goodsDao);
    }

    @Test
    public void testAddOrder() {

        Long goodsId = 1L;
        int quantity = 1;
        long customerId = 12;

        Goods goodsInDB = new Goods(goodsId).setQuantity(100);
        when(goodsDao.selectOne(new Goods(goodsId))).thenReturn(goodsInDB);
        flashSaleService.addOrder(goodsId, quantity, customerId);

        verify(goodsDao).updateByPrimaryKey(goodsInDB.setQuantity(goodsInDB.getQuantity() - quantity));
        verify(orderInfoDao).insert(new OrderInfo(null, customerId, goodsId, quantity, new BigDecimal(5000)));
    }

    @Test(expected = StockException.class)
    public void testAddOrderWithLackOfStack() {

        Long goodsId = 1L;
        int quantity = 2;
        long customerId = 12;

        Goods goodsInDB = new Goods(goodsId).setQuantity(1);
        when(goodsDao.selectOne(new Goods(goodsId))).thenReturn(goodsInDB);
        flashSaleService.addOrder(goodsId, quantity, customerId);

    }
}

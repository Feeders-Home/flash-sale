package com.feeder.flashsale.service.impl;

import java.math.BigDecimal;

import com.feeder.flashsale.dao.GoodsDao;
import com.feeder.flashsale.dao.OrderInfoDao;
import com.feeder.flashsale.entity.OrderInfo;
import com.feeder.flashsale.exception.AppException;
import com.feeder.flashsale.service.flashSale.StockException;
import com.feeder.flashsale.service.FlashSaleService;
import com.google.common.collect.ImmutableMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.feeder.flashsale.service.flashSale.ErrorCode.NOT_ENOUGH_STOCK;

@Service
public class FlashSaleServiceImpl implements FlashSaleService {

    private OrderInfoDao orderInfoDao;
    private GoodsDao goodsDao;

    public FlashSaleServiceImpl(OrderInfoDao orderInfoDao, GoodsDao goodsDao) {
        this.orderInfoDao = orderInfoDao;
        this.goodsDao = goodsDao;
    }


    @Override
    @Transactional(rollbackFor = AppException.class)
    public OrderInfo addOrder(Long goodsId, Integer count, Long customerId) {
        //1.检查商品是否有足够库存
        if (goodsDao.reduceStock(goodsId, count) < 1) {
            throw new StockException(NOT_ENOUGH_STOCK, ImmutableMap.of("goodsId",goodsId));
        }

        //2.增加订单
        OrderInfo orderInfo = new OrderInfo()
                .setCustomerId(customerId)
                .setGoodsId(goodsId)
                .setQuantity(count)
                .setTotalPrice(new BigDecimal(5000));
        orderInfoDao.insert(orderInfo);
        return orderInfo;
    }
}

package com.feeder.flashsale.service.impl;

import java.math.BigDecimal;

import com.feeder.flashsale.dao.GoodsDao;
import com.feeder.flashsale.dao.OrderInfoDao;
import com.feeder.flashsale.entity.Goods;
import com.feeder.flashsale.entity.OrderInfo;
import com.feeder.flashsale.exception.StockException;
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
    @Transactional
    public int addOrder(Long goodsId, Integer quantity, Long customerId) {
        //1.检查商品是否有足够库存
        Goods goods = new Goods(goodsId);
        Goods goodsInDB = goodsDao.selectOne(goods);
        int quantityInDB = goodsInDB.getQuantity();
        if (quantityInDB < quantity) {
            throw new StockException(NOT_ENOUGH_STOCK, ImmutableMap.of("goodsId",goodsId));
        }
        //2.检查订单金额

        //3.减库存
        goodsInDB.setQuantity(quantityInDB - quantity);
        goodsDao.updateByPrimaryKey(goodsInDB);

        //4.增加订单
        OrderInfo orderInfo = new OrderInfo(null, customerId, goodsId, quantity, new BigDecimal(5000));
        return orderInfoDao.insert(orderInfo);
    }
}

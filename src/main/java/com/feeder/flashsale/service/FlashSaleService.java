package com.feeder.flashsale.service;


import com.feeder.flashsale.entity.OrderInfo;

public interface FlashSaleService {

    OrderInfo addOrder(Long goodsId, Integer quantity, Long userId);
}

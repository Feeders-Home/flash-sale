package com.feeder.flashsale.service.impl;

import com.feeder.flashsale.dao.GoodsDao;
import com.feeder.flashsale.entity.Goods;
import com.feeder.flashsale.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao;

    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public Goods getGoodsById(Long id) {
        return goodsDao.selectOne(new Goods(id));
    }
}

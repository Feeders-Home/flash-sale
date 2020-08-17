package com.feeder.flashsale.dao;

import com.feeder.flashsale.entity.Goods;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
@org.apache.ibatis.annotations.Mapper
public interface GoodsDao extends Mapper<Goods> {

    @Update("update goods set quantity=quantity-#{quantity} where quantity >= #{quantity} and id=#{goodsId}")
    Integer reduceStock(long goodsId, int quantity);
}

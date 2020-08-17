package com.feeder.flashsale.dao;

import javax.websocket.server.PathParam;

import com.feeder.flashsale.entity.Goods;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
@org.apache.ibatis.annotations.Mapper
public interface GoodsDao extends Mapper<Goods> {

    @Update("update goods set quantity = quantity - #{count} where id = #{id} and quantity >= #{count}")
    Integer reduceStock(@PathParam("id") Long id, @PathParam("count")Integer count);
}

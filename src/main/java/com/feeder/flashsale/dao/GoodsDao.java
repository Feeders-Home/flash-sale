package com.feeder.flashsale.dao;

import com.feeder.flashsale.entity.Goods;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
@org.apache.ibatis.annotations.Mapper
public interface GoodsDao extends Mapper<Goods> {
}

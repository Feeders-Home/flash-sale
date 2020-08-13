package com.feeder.flashsale.dao;

import com.feeder.flashsale.entity.OrderInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface OrderInfoDao extends Mapper<OrderInfo> {
}

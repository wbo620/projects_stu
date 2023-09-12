package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * User: hallen
 * Date: 2023/9/12
 * Time: 18:53
 */

/**
 * 订单详细表
 */
@Mapper
public interface OrderDetailMapper {

    void insertBatch(ArrayList<OrderDetail> orderDetailList);
}

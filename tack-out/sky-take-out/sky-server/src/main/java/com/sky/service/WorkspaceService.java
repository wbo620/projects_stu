package com.sky.service;

import com.sky.vo.BusinessDataVO;
import com.sky.vo.OrderOverViewVO;

import java.time.LocalDateTime;

/**
 * User: hallen
 * Date: 2023/9/17
 * Time: 09:21
 */

/**
 * 工作台相关
 */

public interface WorkspaceService {
    /**
     * 查询工作台今日数据
     *
     * @param begin
     * @param end
     * @return
     */
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    /**
     * 查询订单数据
     *
     * @return
     */
    OrderOverViewVO getOrderOverView();
}

package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.mapper.WorkspaceMapper;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * User: hallen
 * Date: 2023/9/17
 * Time: 09:21
 */
@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Resource
    private WorkspaceMapper workspaceMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        //查询营业额,有效订单数,订单完成率,有效客单价,新增用户

        Map map = new HashMap();
        map.put("begin", begin);
        map.put("end", end);
        //今日所有订单
        Integer orderCount = orderMapper.orderCountByMap(map);

        map.put("status", Orders.COMPLETED);
        //今日有效订单数
        Integer validOrderCount = orderMapper.orderCountByMap(map);
        //今日营业额
        Double turnover = orderMapper.sumByMap(map);
        //今日平均客单价
        Double unitPrice = 0.0;
        if (turnover != null) {
            unitPrice = turnover / validOrderCount;
        }
        //订单完成率
        Double orderCompletionRate = 0.0;
        if (orderCount != null && orderCount > 0) {
            orderCompletionRate = validOrderCount.doubleValue() / orderCount;
        }

        Map map1 = new HashMap();
        map1.put("end", end);
        //今天的总用户数
        Integer users1 = userMapper.countByMap(map);
        //前一天的总用户数
        map1.put("end", end.plusDays(-1));
        Integer users2 = userMapper.countByMap(map);

        Integer newUsers = 0;
        if (users1 != null && users2 != null) {
            newUsers = users1 - users2;
        }

        return BusinessDataVO.builder()
                .newUsers(newUsers)
                .orderCompletionRate(orderCompletionRate)
                .turnover(turnover)
                .validOrderCount(validOrderCount)
                .unitPrice(unitPrice)
                .build();
    }
}

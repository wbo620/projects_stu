package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.mapper.WorkspaceMapper;
import com.sky.service.WorkspaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.OrderOverViewVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: hallen
 * Date: 2023/9/17
 * Time: 09:21
 */

/**
 * 工作台相关
 */
@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Resource
    private WorkspaceMapper workspaceMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 查询今日数据
     *
     * @param begin
     * @param end
     * @return
     */
    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        //查询营业额,有效订单数,订单完成率,有效客单价,新增用户
        /**
         * 营业额：当日已完成订单的总金额
         * 有效订单：当日已完成订单的数量
         * 订单完成率：有效订单数 / 总订单数
         * 平均客单价：营业额 / 有效订单数
         * 新增用户：当日新增用户的数量
         */

        Map map = new HashMap();
        map.put("begin", begin);
        map.put("end", end);
        //今日所有订单
        Integer totalOrderCount = orderMapper.orderCountByMap(map);

        map.put("status", Orders.COMPLETED);
        //今日有效订单数
        Integer validOrderCount = orderMapper.orderCountByMap(map);
        //今日营业额
        Double turnover = orderMapper.sumByMap(map);
        turnover = turnover == null ? 0.0 : turnover;
        Double unitPrice = 0.0;

        Double orderCompletionRate = 0.0;
        if (totalOrderCount != 0 && validOrderCount != 0) {
            //订单完成率
            orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount;
            //平均客单价
            unitPrice = turnover / validOrderCount;
        }

        //新增用户数
        Integer newUsers = userMapper.countByMap(map);

        return BusinessDataVO.builder()
                .newUsers(newUsers)
                .orderCompletionRate(orderCompletionRate)
                .turnover(turnover)
                .validOrderCount(validOrderCount)
                .unitPrice(unitPrice)
                .build();
    }

    /**
     * 工作台订单管理数据
     *
     * @return
     */
    @Override
    public OrderOverViewVO getOrderOverView() {
        //直接将所有状态信息
        List<Integer> list = orderMapper.getStatus();
        //select count(status) from orders where status=#{status}
        //全部订单
        Integer allOrders = list.size();
        //已完成
        Integer completedOrders = list.stream().filter(status -> status.equals(Orders.COMPLETED)).toArray().length;
        //已取消
        Integer cancelledOrders = list.stream().filter(status -> status.equals(Orders.CANCELLED)).toArray().length;
        //待接单
        Integer waitingOrders = list.stream().filter(status -> status.equals(Orders.TO_BE_CONFIRMED)).toArray().length;
        //待派送
        Integer deliveredOrders = list.stream().filter(status -> status.equals(Orders.CONFIRMED)).toArray().length;
        return OrderOverViewVO.builder()
                .cancelledOrders(cancelledOrders)
                .completedOrders(completedOrders)
                .deliveredOrders(deliveredOrders)
                .allOrders(allOrders)
                .waitingOrders(waitingOrders)
                .build();
    }
}

package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User: hallen
 * Date: 2023/9/9
 * Time: 11:09
 */
@RestController("adminShopController")
@Slf4j
@Api("店铺状态")
@RequestMapping("/admin/shop")
public class ShopController {
    public static final String Key = "SHOP_STATUS";

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取店铺的营业状态
     *
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus() {

        Integer status = (Integer) redisTemplate.opsForValue().get(Key);
        log.info("店铺状态:{} ", status == 1 ? "营业中" : "已打烊");

        return Result.success(status);
    }

    /**
     * 设置店铺营业转台  状态
     *
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public Result updateStatus(@PathVariable Integer status) {
        log.info("设置店铺状态为:{} ", status == 1 ? "营业中" : "已打烊");
        redisTemplate.opsForValue().set(Key, status);
        return Result.success();
    }

}

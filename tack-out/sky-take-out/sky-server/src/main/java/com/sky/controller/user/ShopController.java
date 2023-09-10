package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * User: hallen
 * Date: 2023/9/9
 * Time: 11:49
 */
@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
public class ShopController {
    public static final String Key = "SHOP_STATUS";
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 微信小程序页面获取店铺营业状态
     *
     * @return
     */

    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus() {

        Integer status = (Integer) redisTemplate.opsForValue().get(Key);
        log.info("店铺状态为:{} ", status == 1 ? "营业中" : "已打烊");

        return Result.success(status);
    }
}

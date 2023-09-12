package com.sky.controller.user;

/**
 * User: hallen
 * Date: 2023/9/11
 * Time: 10:25
 */

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车功能
 */
@RestController
@Api(tags = "购物车相关接口")
@RequestMapping("/user/shoppingCart")
public class ShoppingCartConttroller {
    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 添加菜品到购物车
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加到购物车")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 查询购物车,用于小程序端展示
     *
     * @return
     */

    @GetMapping("/list")
    @ApiOperation("查询购物车")
    public Result<List<ShoppingCart>> list() {
        List<ShoppingCart> list = shoppingCartService.showShoppingCart();
        return Result.success(list);
    }

    /**
     * 清空购物车
     *
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public Result cleanAll() {
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }

    /**
     * 减少菜品数量
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("删除购物车中的一个")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }
}

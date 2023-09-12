package com.sky.service;

/**
 * User: hallen
 * Date: 2023/9/11
 * Time: 10:29
 */

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;


/**
 * 购物车
 */
public interface ShoppingCartService {

    /**
     * 添加到购物车
     *
     * @param shoppingCartDTO
     * @return
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查看购物车
     * @return
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * 清空购物车
     */
    void cleanShoppingCart();

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);

}

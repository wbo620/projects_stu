package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User: hallen
 * Date: 2023/9/11
 * Time: 10:34
 */
@Mapper
public interface ShoppingCartMapper {

    /**
     * 动态条件查询
     *
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);


    /**
     * 根据id修改商品数量
     *
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumber(ShoppingCart shoppingCart);

    /**
     * 根据user_id修改商品数量
     *
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where user_id = #{userId}")
    void updateNumberByUsetId(ShoppingCart shoppingCart);

    /**
     * 插入购物车数据
     *
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, user_id, dish_id, setmeal_id, dish_flavor, number, amount, image, create_time) " +
            " values (#{name},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{image},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     * 清空购物车
     *
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id=#{userId}")
    void deleteByUserId(Long userId);

    /**
     * 删除购物车中的一个菜品
     *
     * @param shoppingCart
     */
    void subShoppingCart(ShoppingCart shoppingCart);

    /**
     * 查询购物车中的菜品数量
     *
     * @param shoppingCart
     * @return
     */

    int getNumber(ShoppingCart shoppingCart);

    /**
     * 批量添加购物车中的数据
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}

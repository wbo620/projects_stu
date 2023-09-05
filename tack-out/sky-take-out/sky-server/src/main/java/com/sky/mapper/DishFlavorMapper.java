package com.sky.mapper;

import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * User: hallen
 * Date: 2023/9/6
 * Time: 00:21
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 向口味表插入数据
     * @param flavors 选择口味的集合
     */

    void insertBatch(List<DishFlavor> flavors);
}

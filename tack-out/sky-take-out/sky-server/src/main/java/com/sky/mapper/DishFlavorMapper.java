package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User: hallen
 * Date: 2023/9/6
 * Time: 00:21
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 插入口味信息
     *
     * @param flavors 选择口味的集合
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 查询口味信息
     *
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id=#{dishId}")
    List<DishFlavor> selectFlavor(Long dishId);

    /**
     * 删除旧的口味信息
     *
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id=#{dishId}")
    void delete(Long dishId);

}

package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: hallen
 * Date: 2023/9/6
 * Time: 00:00
 */
@Service
public class DishServiceImpl implements DishService {
    @Resource
    private DishMapper dishMapper;
    @Resource
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 新增菜品合口味
     *
     * @param dishDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        //向菜品表插入一条数据
        dishMapper.insert(dish);
        //获取insert语句生成的主键值
        Long id = dish.getId();

        //向口味表插入多条数据

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors.size() > 0 && flavors != null) {
            flavors.forEach(flavor -> {
                flavor.setDishId(id);
            });

            dishFlavorMapper.insertBatch(flavors);
        }

    }
}

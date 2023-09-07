package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
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
    @Resource
    private SetmealDishMapper setmealDishMapper;

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
        dish.setStatus(1);
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

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        //分页插件

        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        long total = page.getTotal();
        List<DishVO> result = page.getResult();
        return new PageResult(total, result);

    }

    /**
     * 删除菜品
     *
     * @param ids
     */

    @Override
    public void deleteByIds(List<Long> ids) {
        //判断当前菜品是否能够删除---是否存在起售中的菜品？？

        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus().equals(StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //判断当前菜品是否能够删除--是否被套餐关联了？？
        List<Long> setmealIdsByDishIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealIdsByDishIds.size() > 0 && setmealIdsByDishIds != null) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        //删除菜品表中的菜品数据
        for (Long id : ids) {
            dishMapper.delete(id);
            //删除菜品关联的口味数据
            dishFlavorMapper.delete(id);
        }

    }

    /**
     * 启售或禁售
     * @param id
     * @param status
     */
    @Override
    public void statusOrStop(Long id, Integer status) {
        Dish dish = Dish.builder().id(id).status(status).build();
        dishMapper.update(dish);
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Override
    public DishVO getById(Long id) {
        Dish dish = dishMapper.getById(id);
        DishVO dishVO = new DishVO();
        List<DishFlavor> dishFlavors = dishFlavorMapper.selectFlavor(id);
        dishVO.setFlavors(dishFlavors);
        BeanUtils.copyProperties(dish, dishVO);
        return dishVO;
    }

    /**
     * 修改菜品信息
     * @param dishDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAndFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        //更新菜品表的数据
        dishMapper.update(dish);
        //更新口味表的数据

        //把旧口味删除
        dishFlavorMapper.delete(dish.getId());
        //插入新的口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors.size() > 0 && flavors != null) {
            flavors.forEach(flavor -> {
                flavor.setDishId(dish.getId());
            });

        }
        dishFlavorMapper.insertBatch(flavors);
    }
}

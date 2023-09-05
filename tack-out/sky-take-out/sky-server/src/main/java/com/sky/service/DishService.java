package com.sky.service;

import com.sky.dto.DishDTO;
import org.springframework.stereotype.Service;

/**
 * User: hallen
 * Date: 2023/9/5
 * Time: 23:59
 */

public interface DishService {
    /**
     * 新增菜品和口味
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);


}

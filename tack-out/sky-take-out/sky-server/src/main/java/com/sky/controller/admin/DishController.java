package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User: hallen
 * Date: 2023/9/5
 * Time: 23:54
 */

@RestController
@Slf4j
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
public class DishController {

    @Resource
    private DishService dishService;

    @PostMapping
    @ApiOperation("新增菜品")
    public void save(@RequestBody DishDTO dishDTO) {
        dishService.saveWithFlavor(dishDTO);
    }

}

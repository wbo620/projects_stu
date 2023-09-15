package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 根据菜品分类id查询
     *
     * @param categoryId
     * @return
     */

    @GetMapping("/list")
    @ApiOperation("根据分类id查询")
    public Result<List<Dish>> list(String categoryId) {
        List<Dish> list = dishService.getByCategoryId(categoryId);
        return Result.success(list);
    }


    /**
     * 新增菜品
     *
     * @param dishDTO
     */

    @PostMapping
    @ApiOperation("新增菜品")
    public Result<String> save(@RequestBody DishDTO dishDTO) {
        dishService.saveWithFlavor(dishDTO);
        //清除缓存数据
        String key = "dish_" + dishDTO.getCategoryId();
        cleanCache(key);
        return Result.success();
    }

    /**
     * 分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> getPage(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询");
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 删除菜品
     *
     * @param ids
     */
    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public Result<String> deleteByIds(@RequestParam List<Long> ids) {
        dishService.deleteByIds(ids);
        //清除缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 修改菜品状态,启售/禁售
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("修改菜品状态")
    public Result<PageResult> update(@PathVariable Integer status, Long id) {
        dishService.statusOrStop(id, status);

        //清除缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 根据id查询菜品
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }


    /**
     * 更新菜品信息
     *
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品和口味的信息")
    public Result update(@RequestBody DishDTO dishDTO) {
        dishService.updateAndFlavor(dishDTO);

        //清除缓存数据
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 清除缓存数据
     *
     * @param partter
     */
    private void cleanCache(String partter) {
        Set keys = redisTemplate.keys(partter);
        redisTemplate.delete(keys);
    }
}

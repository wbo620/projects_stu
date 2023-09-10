package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     *
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品
     *
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id删除菜品
     *
     * @param id
     */
    @Delete("delete from dish where id = #{id}")
    void delete(Long id);

    /**
     * 更新菜品信息
     *
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 查询菜品信息
     *
     * @param id
     * @return
     */

    @Select("select * from dish where" +
            " id = #{id}")
    Dish getById(Long id);

    /**
     * 按照菜品分类查询(客户端)
     *
     * @param dish
     * @return
     */

    List<Dish> list(Dish dish);

    /**
     * 根据分类id查询菜品(管理端)
     *
     * @param categoryId
     * @return
     */
    @Select("select * from dish where category_id=#{categoryId}")
    List<Dish> getByCategoryId(String categoryId);

    @Select("select a.* " +
            "from dish a " +
            "left join setmeal_dish b " +
            "on a.id = b.dish_id " +
            "where b.setmeal_id = #{id}")
    List<Dish> getBySetmealId(Long id);
}

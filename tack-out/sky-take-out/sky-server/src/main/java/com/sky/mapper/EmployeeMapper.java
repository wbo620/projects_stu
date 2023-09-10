package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @AutoFill(value = OperationType.INSERT)

    @Insert("insert into employee " +
            "(name, username, password, phone, sex, id_number, create_time, update_time,create_user,update_user) " +
            "values (#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber},#{createTime}, #{updateTime},#{createUser},#{updateUser})")
    boolean insertEmployee(Employee employee);

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */

    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工状态
     *
     * @param employe
     */


    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employe);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */

    @Select("select id ,name, username, password, phone, sex, id_number, create_user, update_time,create_time,update_user" +
            " from employee" +
            " where id=#{id} ")
    Employee selectById(Long id);


}

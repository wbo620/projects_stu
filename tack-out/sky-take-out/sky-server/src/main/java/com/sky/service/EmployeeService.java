package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 添加员工
     *
     * @param employeeDTO
     * @return
     */

    boolean addEmployee(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工状态
     *
     * @param status
     * @param id
     */

    void statusOrStop(Integer status, Long id);

    /**
     * 根据ID查询员工
     *
     * @param id
     * @return
     */

    Employee queryById(Long id);

    /**
     * 编辑员工信息
     *
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}

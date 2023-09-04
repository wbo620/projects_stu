package com.ice.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.usercenter.common.ErrorCode;
import com.ice.usercenter.exception.BusinessException;
import com.ice.usercenter.mapper.UserMapper;
import com.ice.usercenter.modle.domain.User;
import com.ice.usercenter.service.SelectService;

import javax.annotation.Resource;

/**
 * User: hallen
 * Date: 2023/9/2
 * Time: 23:07
 */

public class SelectServiceImpl extends ServiceImpl<UserMapper, User> implements SelectService {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @Override
    public User selectUserByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if (user==null) {
            throw new BusinessException(ErrorCode.NULL_ERROR,"用户名不存在");
        }
        User safetyUser = new UserServiceImpl().getSafetyUser(user);
        return safetyUser;
    }

    @Override
    public User selectUserByAccount(String userAccount) {
        return null;
    }

    @Override
    public User selectUserByPhone(String phone) {
        return null;
    }

    @Override
    public User selectUserByGender(String gender) {
        return null;
    }

    @Override
    public User selectUserByEmail(String email) {
        return null;
    }

    @Override
    public User selectUserByStatus(String status) {
        return null;
    }

    @Override
    public User selectUserByUserRole(String userRole) {
        return null;
    }

    @Override
    public User selectUserByPlanetCode(String planetCode) {
        return null;
    }
}

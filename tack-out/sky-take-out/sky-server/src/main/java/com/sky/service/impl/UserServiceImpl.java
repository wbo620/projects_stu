package com.sky.service.impl;

import com.sky.entity.User;
import com.sky.mapper.UserMapper;
import com.sky.service.UserService;
import com.sky.vo.UserLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * User: hallen
 * Date: 2023/9/9
 * Time: 15:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询用户是否存在
     *
     * @param openid
     * @return
     */

    @Override
    public int getById(String openid) {

        int count = userMapper.getUserById(openid);
        if (count != 0) {
            return count;
        }
        return 0;
    }

    /**
     * 保存用户
     *
     * @param openid
     */
    @Override
    public void save(String openid) {
        User user = new User();
        user.setOpenid(openid);
        //user.setName();
        // user.setPhone();
        user.setCreateTime(LocalDateTime.now());
        userMapper.saveUser(user);
    }

    /**
     * 获取用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public UserLoginVO getUserByCode(String openid) {
        User user = userMapper.getByOpenid(openid);
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        //userLoginVO.setToken();
        return userLoginVO;
    }
}

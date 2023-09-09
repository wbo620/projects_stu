package com.sky.service;

import com.sky.vo.UserLoginVO;

/**
 * User: hallen
 * Date: 2023/9/9
 * Time: 15:47
 */
public interface UserService {

    int getById(String openid);

    void save(String openid);

    UserLoginVO getUserByCode(String openid);
}

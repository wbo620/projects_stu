package com.sky.service;

import com.sky.vo.UserLoginVO;

/**
 * User: hallen
 * Date: 2023/9/9
 * Time: 15:47
 */
public interface UserService {

    /**
     * 根据id查询用户是否存在
     *
     * @param openid
     * @return
     */
    int getById(String openid);

    /**
     * 保存用户信息
     *
     * @param openid
     */
    void save(String openid);

    /**
     * 获取用户信息
     *
     * @param openid
     * @return
     */

    UserLoginVO getUserByCode(String openid);
}

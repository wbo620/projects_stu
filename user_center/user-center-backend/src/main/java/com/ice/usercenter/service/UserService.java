package com.ice.usercenter.service;

import com.ice.usercenter.modle.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
* @author hallen
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-08-28 21:19:04
*/
public interface UserService extends IService<User> {
  /**
   * 用户注册
   * @param userAccount 用户账户
   * @param userPassword 用户密码
   * @param checkPassword 校验密码
   * @param planetCode 星球编号
   * @return 用户id
   */
  long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

  /**
   * 用户登录
   * @param userAccount 用户账户
   * @param userPassword 用户密码
   * @param request
   * @return
   */

  User login(String userAccount, String userPassword, HttpServletRequest request);

  /**
   * 用户脱敏
   * @param originUser 原始信息
   * @return用户脱敏信息
   */
  User getSafetyUser(User originUser);

  /**
   *  用户退出
   * @param request
   * @return
   */

  int logout(HttpServletRequest request);
}

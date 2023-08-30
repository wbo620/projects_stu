package com.ice.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ice.usercenter.modle.domain.User;
import com.ice.usercenter.modle.domain.request.UserLoginRequest;
import com.ice.usercenter.modle.domain.request.UserRegisterRequest;
import com.ice.usercenter.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ice.usercenter.contant.UserConstant.ADMIN_USER;
import static com.ice.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * User: hallen
 * Date: 2023/8/29
 * Time: 21:31
 */

/**
 * 用户服务接口
 */
@RestController
@RequestMapping("/user")
public class userController {

  @Resource
  private UserService userService;

  /**
   * 注册
   *
   * @param userRegisterRequest
   * @return
   */

  @PostMapping("/register")
  public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
    if (userRegisterRequest == null) {
      return null;
    }

    String userAccount = userRegisterRequest.getUserAccount();
    String userPassword = userRegisterRequest.getUserPassword();
    String checkPassword = userRegisterRequest.getCheckPassword();
    if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
      return null;
    }
    return userService.userRegister(userAccount, userPassword, checkPassword);

  }

  /**
   * 获取登录态
   *
   * @param request 请求域
   * @return 返回当前登录用户的脱敏信息
   */
  @GetMapping("/current")
  public User getCurrentUser(HttpServletRequest request) {
    User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
    if (currentUser == null) {
      return null;
    }
    Long userId = currentUser.getId();
    //todo 检验用户是否合法
    User user = userService.getById(userId);

    return userService.getSafetyUser(user);
  }

  /**
   * 登录
   *
   * @param userLoginRequest 用户登录参数json
   * @param request 请求域
   * @return 返回用户脱敏信息
   */
  @PostMapping("/login")
  public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
    if (userLoginRequest == null) {
      return null;
    }
    String userAccount = userLoginRequest.getUserAccount();
    String userPassword = userLoginRequest.getUserPassword();

    if (StringUtils.isAnyBlank(userAccount, userPassword)) {
      return null;
    }
    return userService.login(userAccount, userPassword, request);
  }

  /**
   * 查找
   *
   * @param username 用户名
   * @param request 请求域
   * @return 返回脱敏后的用户集合
   */
  @GetMapping("/search")
  public List<User> searchUsers(String username, HttpServletRequest request) {
    //获取用户身份
    if (!isAdmin(request)) {
      return new ArrayList<User>();
    }
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotBlank(username)) {
      queryWrapper.like("username", username);
    }

    List<User> userList = userService.list(queryWrapper);
    //用户脱敏
    return userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
  }

  /**
   * 根据id删除用户
   *
   * @param id
   * @param request
   * @return
   */
  @GetMapping("/delete")
  public boolean removeUsers(long id, HttpServletRequest request) {
    //获取用户身份
    if (!isAdmin(request)) {
      return false;
    }
    if (id <= 0) {
      return false;
    }
    return userService.removeById(id);
  }

  /**
   * 是否为管理员
   *
   * @param request
   * @return
   */
  public boolean isAdmin(HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
    if (user == null || user.getUserRole() != ADMIN_USER) {
      return false;
    }
    return true;
  }

}

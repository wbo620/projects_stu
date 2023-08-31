package com.ice.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ice.usercenter.common.BaseResponse;
import com.ice.usercenter.common.ErrorCode;
import com.ice.usercenter.exception.BusinessException;
import com.ice.usercenter.modle.domain.User;
import com.ice.usercenter.modle.domain.request.UserLoginRequest;
import com.ice.usercenter.modle.domain.request.UserRegisterRequest;
import com.ice.usercenter.service.UserService;
import com.ice.usercenter.common.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            //
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);

    }

    /**
     * 获取登录态
     *
     * @param request 请求域
     * @return 返回当前登录用户的脱敏信息
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        Long userId = currentUser.getId();
        //todo 检验用户是否合法
        User user = userService.getById(userId);

        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    /**
     * 登录
     *
     * @param userLoginRequest 用户登录参数json
     * @param request          请求域
     * @return 返回用户脱敏信息
     */
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        User user = userService.login(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int logout = userService.logout(request);
        return ResultUtils.success(logout);
    }

    /**
     * 查找
     *
     * @param username 用户名
     * @param request  请求域
     * @return 返回脱敏后的用户集合
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        //获取用户是否为管理员身份
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }

        List<User> userList = userService.list(queryWrapper);
        //用户脱敏
        List<User> users = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(users);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public BaseResponse<Boolean> removeUsers(long id, HttpServletRequest request) {
        //获取用户身份
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id小于0");
        }
        boolean remove = userService.removeById(id);
        return ResultUtils.success(remove);
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

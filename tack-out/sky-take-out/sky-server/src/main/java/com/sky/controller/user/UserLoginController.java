package com.sky.controller.user;

import com.sky.dto.UserLoginDTO;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.vo.UserLoginVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * User: hallen
 * Date: 2023/9/9
 * Time: 15:35
 */
@Controller
@Slf4j
@Api(tags = "用户登录")
@RequestMapping("/user/user")
@ResponseBody
public class UserLoginController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {

        if (userLoginDTO == null) {
            return Result.error("登录失败");
        }
        //查询数据库用户是否存在
        int count = userService.getById(userLoginDTO.getCode());
        if (count == 0) {
            //不存在,向数据库中插入数据
            userService.save(userLoginDTO.getCode());
        }
        //存在,获取用户信息
        UserLoginVO userLoginVO = userService.getUserByCode(userLoginDTO.getCode());


        return Result.success(userLoginVO);
    }
}

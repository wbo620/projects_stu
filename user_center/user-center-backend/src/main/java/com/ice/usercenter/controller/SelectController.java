package com.ice.usercenter.controller;

import com.ice.usercenter.common.BaseResponse;
import com.ice.usercenter.common.ResultUtils;
import com.ice.usercenter.modle.domain.User;
import com.ice.usercenter.service.SelectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * User: hallen
 * Date: 2023/9/2
 * Time: 23:21
 */
@RequestMapping("/select")
public class SelectController {
    @Resource
    private SelectService selectService;

    @GetMapping("/byname")
    public BaseResponse<User> queryByusername(String username){
        User user = selectService.selectUserByName(username);

       return ResultUtils.success(user);
    }
}

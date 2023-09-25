package com.ice.soso.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.common.PageRequest;
import com.ice.soso.model.dto.user.UserQueryRequest;
import com.ice.soso.model.vo.UserVO;
import com.ice.soso.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: hallen
 * Date: 2023/9/25
 * Time: 10:01
 */

/**
 * 用户数据源接口
 */
@Service
@Slf4j
public class UserDataSource implements DataSource<UserVO> {
    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(pageNum);
        userQueryRequest.setPageSize(pageSize);

        return userService.listUserVOByPage(userQueryRequest);
    }
}

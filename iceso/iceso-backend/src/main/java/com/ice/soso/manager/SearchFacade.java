package com.ice.soso.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.common.ErrorCode;
import com.ice.soso.exception.ThrowUtils;
import com.ice.soso.model.dto.post.PostQueryRequest;
import com.ice.soso.model.dto.user.UserQueryRequest;
import com.ice.soso.model.entity.Post;
import com.ice.soso.model.entity.User;
import com.ice.soso.model.enums.SearchTypeEnum;
import com.ice.soso.model.search.SearchRequest;
import com.ice.soso.model.vo.PostVO;
import com.ice.soso.model.vo.SearchVO;
import com.ice.soso.model.vo.UserVO;
import com.ice.soso.service.PostService;
import com.ice.soso.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Picture;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * User: hallen
 * Date: 2023/9/22
 * Time: 16:24
 */
@Slf4j
@Component
public class SearchFacade {

    @Resource
    private PostService postService;
    @Resource
    private UserService userService;
    public SearchVO searchAll(SearchRequest searchRequest, HttpServletRequest request) {
       //获取搜索数据的类型
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        ThrowUtils.throwIf(StringUtils.isBlank(type), ErrorCode.PARAMS_ERROR);

        String searchText = searchRequest.getSearchText();
        long current = searchRequest.getCurrent();
        long size = searchRequest.getPageSize();


        //TODO 重复代码待优化
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(1);
        postQueryRequest.setPageSize(10);

        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(1);
        userQueryRequest.setPageSize(10);

        Page<Post> postPage = postService.page(new Page<>(current, size),
                postService.getQueryWrapper(postQueryRequest));
        Page<PostVO> postVOPage = postService.getPostVOPage(postPage, request);

        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
        List<UserVO> userVO = userService.getUserVO(userPage.getRecords());

        //TODO 待优化,爬取必应的图片
        List<Picture> pictureList = new ArrayList<>();

        SearchVO searchVO = new SearchVO();

        searchVO.setPostList(postVOPage.getRecords());
        searchVO.setUserList(userVO);
        searchVO.setPictureList(pictureList);

        return searchVO;
    }
}

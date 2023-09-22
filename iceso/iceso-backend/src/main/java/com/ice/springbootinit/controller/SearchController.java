package com.ice.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.ice.springbootinit.common.BaseResponse;
import com.ice.springbootinit.common.ErrorCode;
import com.ice.springbootinit.common.ResultUtils;
import com.ice.springbootinit.exception.ThrowUtils;
import com.ice.springbootinit.manager.SearchFacade;
import com.ice.springbootinit.model.dto.post.PostQueryRequest;
import com.ice.springbootinit.model.entity.Post;
import com.ice.springbootinit.model.search.SearchRequest;
import com.ice.springbootinit.model.vo.PostVO;
import com.ice.springbootinit.model.vo.SearchVO;
import com.ice.springbootinit.service.PictureService;
import com.ice.springbootinit.service.PostService;
import com.ice.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子接口
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private PictureService pictureService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        return ResultUtils.success(searchFacade.searchAll(searchRequest, request));
    }

}


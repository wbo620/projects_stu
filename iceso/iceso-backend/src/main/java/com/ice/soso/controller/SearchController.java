package com.ice.soso.controller;

import com.ice.soso.common.BaseResponse;
import com.ice.soso.common.ResultUtils;
import com.ice.soso.manager.SearchFacade;
import com.ice.soso.model.search.SearchRequest;
import com.ice.soso.model.vo.SearchVO;
import com.ice.soso.service.PictureService;
import com.ice.soso.service.PostService;
import com.ice.soso.service.UserService;
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


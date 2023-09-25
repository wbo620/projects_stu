package com.ice.soso.controller;

import com.ice.soso.common.BaseResponse;
import com.ice.soso.common.ResultUtils;
import com.ice.soso.manager.SearchFacade;
import com.ice.soso.model.search.SearchRequest;
import com.ice.soso.model.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 帖子接口
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {
    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) throws IOException {
        SearchVO searchVO = searchFacade.searchAll(searchRequest, request);
        return ResultUtils.success(searchVO);

    }

}


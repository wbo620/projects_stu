package com.ice.soso.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.common.BaseResponse;
import com.ice.soso.common.ErrorCode;
import com.ice.soso.common.ResultUtils;
import com.ice.soso.exception.BusinessException;
import com.ice.soso.manager.SearchFacade;
import com.ice.soso.model.dto.post.PostQueryRequest;
import com.ice.soso.model.dto.user.UserQueryRequest;
import com.ice.soso.model.entity.Picture;
import com.ice.soso.model.search.SearchRequest;
import com.ice.soso.model.vo.PostVO;
import com.ice.soso.model.vo.SearchVO;
import com.ice.soso.model.vo.UserVO;
import com.ice.soso.service.PictureService;
import com.ice.soso.service.PostService;
import com.ice.soso.service.UserService;
import com.thoughtworks.xstream.core.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequest searchRequest, HttpServletRequest request) throws IOException {
        String searchText = searchRequest.getSearchText();
        //并发
        CompletableFuture<Page<PostVO>> postTask = CompletableFuture.supplyAsync(() -> {
            PostQueryRequest postQueryRequest = new PostQueryRequest();
            postQueryRequest.setSearchText(searchText);
            Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, request);
            return postVOPage;
        });

        CompletableFuture<Page<UserVO>> userTask = CompletableFuture.supplyAsync(() -> {
            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setUserName(searchText);
            Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);
            return userVOPage;
        });

        CompletableFuture<Page<Picture>> pictureTask = CompletableFuture.supplyAsync(() -> {
            Page<Picture> picturePage = null;
            try {
                picturePage = pictureService.searchPicture(searchText, 1, 10);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return picturePage;
        });

        //三个线程全部结束才进行下面的操作
        CompletableFuture.allOf(postTask, userTask, pictureTask).join();

        try {
            Page<PostVO> postVOPage = postTask.get();
            Page<UserVO> userVOPage = userTask.get();
            Page<Picture> picturePage = pictureTask.get();
            SearchVO searchVO = new SearchVO();
            searchVO.setPostList(postVOPage.getRecords());
            searchVO.setUserList(userVOPage.getRecords());
            searchVO.setPictureList(picturePage.getRecords());

            return ResultUtils.success(searchVO);
        } catch (Exception e) {
            log.error("查询异常", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "查询异常");
        }

    }

}


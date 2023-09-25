package com.ice.soso.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.common.ErrorCode;
import com.ice.soso.datasource.DataSource;
import com.ice.soso.datasource.DataSourceRegistry;
import com.ice.soso.exception.BusinessException;
import com.ice.soso.exception.ThrowUtils;
import com.ice.soso.model.dto.post.PostQueryRequest;
import com.ice.soso.model.dto.user.UserQueryRequest;
import com.ice.soso.model.entity.Picture;
import com.ice.soso.model.enums.SearchTypeEnum;
import com.ice.soso.model.search.SearchRequest;
import com.ice.soso.model.vo.PostVO;
import com.ice.soso.model.vo.SearchVO;
import com.ice.soso.model.vo.UserVO;
import com.ice.soso.service.PictureService;
import com.ice.soso.service.PostService;
import com.ice.soso.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.concurrent.CompletableFuture;

/**
 * User: hallen
 * Date: 2023/9/22
 * Time: 16:24
 */

/**
 * 搜索门面
 */
@Slf4j
@Component
public class SearchFacade {

    @Resource
    private PostService postService;
    @Resource
    private UserService userService;
    @Resource
    private PictureService pictureService;
    @Resource
    private DataSourceRegistry dataSourceRegistry;

    public SearchVO searchAll(SearchRequest searchRequest, HttpServletRequest request) throws IOException {
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        ThrowUtils.throwIf(StringUtils.isBlank(type), ErrorCode.PARAMS_ERROR);
        String searchText = searchRequest.getSearchText();
        long current = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();
        //如果搜索类型为空,需要加载全部类型数据
        if (searchTypeEnum == null) {

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

                return searchVO;
            } catch (Exception e) {
                log.error("查询异常", e);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "查询异常");
            }
        } else {
            SearchVO searchVO = new SearchVO();
            DataSource<?> dataSourceByType = dataSourceRegistry.getDataSourceByType(type);
            Page<?> dataPage = dataSourceByType.doSearch(searchText, current, pageSize);
            searchVO.setDataList(dataPage.getRecords());
            return searchVO;
        }
    }
}

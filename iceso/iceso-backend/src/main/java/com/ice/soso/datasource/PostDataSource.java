package com.ice.soso.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.model.dto.post.PostQueryRequest;
import com.ice.soso.model.entity.Post;
import com.ice.soso.model.vo.PostVO;
import com.ice.soso.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * User: hallen
 * Date: 2023/9/25
 * Time: 10:01
 */

/**
 * 文章数据接口
 */
@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {
    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNum, long pageSize) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setPageSize(pageSize);
        postQueryRequest.setCurrent(pageNum);

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return postService.getPostVOPage(postPage,request);
    }
}

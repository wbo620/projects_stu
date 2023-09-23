package com.ice.soso.manager;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.common.ErrorCode;
import com.ice.soso.exception.ThrowUtils;
import com.ice.soso.model.dto.post.PostQueryRequest;
import com.ice.soso.model.dto.user.UserQueryRequest;
import com.ice.soso.model.entity.Picture;
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


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public SearchVO searchAll(SearchRequest searchRequest, HttpServletRequest request) throws IOException {
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
        //String url = "http://www.iyinghua.com";
        //Document doc = Jsoup.connect(url).get();
        //Elements elements = doc.select(".img").select("img");
        ////System.out.println(elements);
        //Map<String, String> map = new HashMap<>();
        //
        //for (Element e : elements) {
        //    String picUrl = e.attr("src");
        //    String title = e.attr("alt");
        //    //System.out.println(picUrl);
        //    // System.out.println(alt);
        //    map.put(title, picUrl);
        //
        //}
        //List<Picture> pictureList = new ArrayList<>();
        //
        //map.forEach((title, picUrl) -> {
        //    Picture picture = new Picture();
        //    picture.setUrl(picUrl);
        //    picture.setTitle(title);
        //    pictureList.add(picture);
        //});
        String url = String.format("https://cn.bing.com/images/search?q=%s&first=%s", searchText, current);
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".iuscp.isv");
        List<Picture> pictureList = new ArrayList<>();
        for (Element element : elements) {
            //System.out.println(element);
            String m = element.select(".iusc").get(0).attr("m");
            // System.out.println(m);

            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            //取图片地址
            String murl = (String) map.get("murl");
            //取标题
            String title = element.select(".inflnk").get(0).attr("aria-label");
            //System.out.println(title);
            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(murl);
            pictureList.add(picture);
        }
        SearchVO searchVO = new SearchVO();

        searchVO.setPostList(postVOPage.getRecords());
        searchVO.setUserList(userVO);
        searchVO.setPictureList(pictureList);


        return searchVO;
    }
}

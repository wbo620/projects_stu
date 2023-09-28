package com.ice.soso;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.datasource.DataSource;
import com.ice.soso.model.entity.Video;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: hallen
 * Date: 2023/9/25
 * Time: 10:01
 */

/**
 * bilibili视频爬取
 * bug 视频封面不显示
 */

public class VideoDataSource2 implements DataSource<Video> {
    @Override
    public Page<Video> doSearch(String searchText, long pageNum, long pageSize) throws IOException {
        String url = String.format("https://search.bilibili.com/all?keyword=%s&from_source=webtop_search&spm_id_from=333.1007&search_source=%s", searchText, pageNum);
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".bili-video-card");
        //Elements elements = doc.select(".bili-video-card__info--right");
        List<Video> videoList = new ArrayList<>();
        for (Element element : elements) {
            // System.out.println(element);

            //标题
            String title = element.select("h3").attr("title");
            // System.out.println(title);

            //视频地址
            String href = element.select("a").attr("href");
            String tureHref = href.substring(2, href.length());
            //System.out.println(tureHref);

            //封面地址
            //String picture = element.select("picture").get(0).child(1).attr("srcset");
            String picture = element.select("img").attr("src");
            //System.out.println(picture);
            //String s = picture.split("@")[0];
            String picUrl = "https:".concat(picture);

            Video video = new Video();
            video.setTitle(title);
            video.setUrl(href);
            video.setPicUrl(picUrl);
            //video.setUrl(tureHref);
            //video.setPicUrl(picUrl);
            videoList.add(video);

            if (videoList.size() >= pageSize) {
                break;
            }
        }
        Page<Video> VideoPage = new Page<>(pageNum, pageSize);
        VideoPage.setRecords(videoList);
        return VideoPage;
    }
}

package com.ice.soso.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.model.entity.Video;
import com.ice.soso.model.entity.Video;
import com.ice.soso.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: hallen
 * Date: 2023/9/25
 * Time: 15:16
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {
    @Override
    public Page<Video> searchVideo(String searchText, long pageNum, long pageSize) throws IOException {
        long current = (pageNum - 1) * pageSize;
        String url = String.format("https://search.bilibili.com/all?keyword=%s&from_source=webtop_search&spm_id_from=333.1007&search_source=%s", searchText, current);
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".bili-video-card__info--right");
        List<Video> videoList = new ArrayList<>();
        for (Element element : elements) {
            //System.out.println(element);

            String title = element.select("h3").attr("title");

           // System.out.println(title);

            String href = element.select("a").attr("href");
            String tureHref = href.substring(2, href.length());
           // System.out.println(tureHref);

            Video video = new Video();
            video.setTitle(title);
            video.setUrl(tureHref);
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

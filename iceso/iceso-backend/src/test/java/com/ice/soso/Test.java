package com.ice.soso;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.model.entity.Video;
import com.ice.soso.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.transform.Source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: hallen
 * Date: 2023/9/25
 * Time: 15:26
 */

public class Test {
    public static void main(String[] args) throws IOException {
        long pageNum = 1;
        long pageSize = 10;
        String searchText = "小黑子";

        long current = (pageNum - 1) * pageSize;
        String url = String.format("https://search.bilibili.com/all?keyword=%s&from_source=webtop_search&spm_id_from=333.1007&search_source=%s", searchText, current);
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".bili-video-card");
        List<Video> videoList = new ArrayList<>();
        for (Element element : elements) {
          // System.out.println(element);
            String picturl = element.select("img").attr("src");
            String s = picturl.split("@")[0];
            String picUrl = s.substring(2, s.length());

            String title = element.select("h3").attr("title");
            //
            //System.out.println(title);
            //
            String href = element.select(".v-img.bili-video-card__cover").attr("source");
            String tureHref = href.substring(2, href.length());
            System.out.println(tureHref);

            //    Video video = new Video();
            //    video.setTitle(title);
            //    video.setUrl(tureHref);
            //    video.setPicUrl(picUrl);
            //    videoList.add(video);
            //
            //    if (videoList.size() >= pageSize) {
            //        break;
            //    }
            //}
            //Page<Video> VideoPage = new Page<>(pageNum, pageSize);
            //VideoPage.setRecords(videoList);
            //return VideoPage;
        }
    }
}


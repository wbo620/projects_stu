package com.ice.soso;

import cn.hutool.json.JSONUtil;
import com.ice.soso.model.entity.Video;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: hallen
 * Date: 2023/9/25
 * Time: 20:15
 */

public class Text3 {
    public static void main(String[] args) throws IOException {
        long current = 1;
        String searchText = "小黑子";
        String url = String.format("https://search.bilibili.com/all?keyword=%s&from_source=webtop_search&spm_id_from=333.1007&search_source=%s", searchText, current);
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".bili-video-card");
        //Elements elements = doc.select(".bili-video-card__info--right");
        List<Video> videoList = new ArrayList<>();
        for (Element element : elements) {
           // System.out.println(element);

            String title = element.select("h3").attr("title");
            // System.out.println(title);

            String href = element.select("a").attr("href");
            String tureHref = href.substring(2, href.length());
             //System.out.println(tureHref);

            //String picture = element.select("picture").get(0).child(1).attr("srcset");
            ////System.out.println(picture);
            //String picUrl = "https:".concat(picture);

            String picture = element.select("img").attr("src");
            System.out.println(picture);
        }
    }
}

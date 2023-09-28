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
 * Time: 19:28
 */

public class Test2 {
    public static void main(String[] args) throws IOException {
        long pageNum = 1;
        long pageSize = 10;
        String searchText = "小黑子";
        String url = String.format("https://cn.bing.com/videos/search?q=%s&FORM=VDRE", searchText);
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".dg_u");
        List<Video> videoList = new ArrayList<>();
        for (Element element : elements) {
            // System.out.println(element);
            //视频标题
            String title = element.select(".mc_vtvc_title.b_promtxt").attr("title");
            //System.out.println(title);
            //视频地址
            String href = element.select("a").attr("href");
            //String tureHref = href.substring(2, href.length());
            //String tureHref = "https:".concat(href);
            // System.out.println(tureHref);
            //视频封面图片地址
            String m = element.select(".mc_vtvc_con_rc").get(0).attr("vscm");

            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            System.out.println(map);
            String picturl = (String) map.get("turl");
            //取图片地址
        }
    }
}

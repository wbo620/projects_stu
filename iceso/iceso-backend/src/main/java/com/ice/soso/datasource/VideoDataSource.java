package com.ice.soso.datasource;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.model.entity.Video;
import com.sun.xml.internal.bind.v2.TODO;
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
 * Time: 10:01
 */

/**
 * 视频数据源接口
 */
@Service
public class VideoDataSource implements DataSource<Video> {
    //TODO 更改视频接口为b站
    @Override
    public Page<Video> doSearch(String searchText, long pageNum, long pageSize) throws IOException {
        if (searchText == null) {
            searchText = "世界旅游胜地";
        }
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
            // String href = element.select(".mc_vtvc_con_rc").attr("href");
            //String tureHref = href.substring(2, href.length());
            //String tureHref = "https:".concat(href);
            // System.out.println(tureHref);
            //视频封面图片地址
            String m = element.select(".mc_vtvc_con_rc").get(0).attr("vscm");

            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            //System.out.println(map);
            //取图片地址
            String picturl = (String) map.get("turl");
            //取视频地址
            String href = (String) map.get("murl");
            //String s = picturl.split("@")[0];
            //String picUrl = "https:".concat(picturl)+".avif";
            Video video = new Video();
            video.setTitle(title);
            video.setUrl(href);
            video.setPicUrl(picturl);
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

package com.ice.soso.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.model.entity.Picture;
import com.ice.soso.model.entity.Video;

import java.io.IOException;


/**
 * 图片服务
 *
 */
public interface VideoService {

    Page<Video> searchVideo(String searchText, long pageNum, long pageSize) throws IOException;
}

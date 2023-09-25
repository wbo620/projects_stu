package com.ice.soso.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

/**
 * User: hallen
 * Date: 2023/9/25
 * Time: 10:01
 */

/**
 * 视频数据源接口
 *
 */
@Service
public class VideoDataSource implements DataSource {
    @Override
    public Page doSearch(String searchText, long pageNum, long pageSize) {
        return null;
    }
}

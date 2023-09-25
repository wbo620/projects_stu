package com.ice.soso.datasource;



/**
 * User: hallen
 * Date: 2023/9/25
 * Time: 09:59
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.IOException;

/**
 * 数据源接口（新接入的数据源必须实现）
 *
 * @param <T>
 */
public interface DataSource<T> {

    /**
     * 搜索
     *
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<T> doSearch(String searchText, long pageNum, long pageSize) throws IOException;
}

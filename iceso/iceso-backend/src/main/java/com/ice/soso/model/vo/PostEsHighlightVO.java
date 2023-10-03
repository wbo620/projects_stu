package com.ice.soso.model.vo;

import lombok.Data;

/**
 * User: hallen
 * Date: 2023/10/3
 * Time: 17:40
 */

/**
 * 搜索关键词高亮
 *
 * @author lacy
 */
@Data
public class PostEsHighlightVO {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
}


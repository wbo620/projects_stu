package com.ice.soso.model.vo;

import com.ice.soso.model.entity.Picture;
import com.ice.soso.model.entity.Video;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

/**
 * User: hallen
 * Date: 2023/9/22
 * Time: 15:27
 */

/**
 * 聚合搜索
 *
 */
@Data
public class SearchVO implements Serializable {

    private List<UserVO> userList;

    private List<PostVO> postList;

    private List<Picture> pictureList;
    private List<Video> videoList;

    private List<?> dataList;

    private static final long serialVersionUID = 1L;

}

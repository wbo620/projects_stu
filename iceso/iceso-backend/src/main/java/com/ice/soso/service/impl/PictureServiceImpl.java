package com.ice.soso.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.soso.model.entity.Picture;
import com.ice.soso.service.PictureService;
import org.springframework.stereotype.Service;

/**
 * User: hallen
 * Date: 2023/9/23
 * Time: 17:13
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Override
    public Page<Picture> searchPicture(String searchText, long pageNum, long pageSize) {
        return null;
    }
}

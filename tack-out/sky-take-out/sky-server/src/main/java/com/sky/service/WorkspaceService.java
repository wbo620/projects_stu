package com.sky.service;

import com.sky.vo.BusinessDataVO;

import java.time.LocalDateTime;

/**
 * User: hallen
 * Date: 2023/9/17
 * Time: 09:21
 */

public interface WorkspaceService {
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

}

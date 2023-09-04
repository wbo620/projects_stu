package com.ice.usercenter.common;

import com.ice.usercenter.common.BaseResponse;

/**
 * User: hallen
 * Date: 2023/8/31
 * Time: 22:20
 */

/**
 * 返回的工具类
 *
 * @author wbo
 */
public class ResultUtils {
    /**
     * 返回成功的信息
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 返回失败的信息
     *
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getCode(), null,message, description);
    }

    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse<>(code,null, message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(),null, errorCode.getMessage(), description);
    }
}

package com.ice.usercenter.exception;

import com.ice.usercenter.common.ErrorCode;

/**
 * User: hallen
 * Date: 2023/8/31
 * Time: 22:55
 */

/**
 * 自定义异常类
 */
public class BusinessException extends RuntimeException {
    /**
     * 状态码
     */
    private final int code;

    /**
     * 异常信息的详细
     */
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

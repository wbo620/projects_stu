package com.ice.usercenter.exception;

import com.ice.usercenter.common.BaseResponse;
import com.ice.usercenter.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * User: hallen
 * Date: 2023/8/31
 * Time: 23:24
 */

/**
 * 全局异常处理器
 *
 * @author wbo
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return new BaseResponse(e.getCode(),null, e.getMessage(),e.getDescription());
    }


    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runTimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return new BaseResponse(ErrorCode.SYSTEM_ERROR);
    }
}

package csu.yulin.springboot3template.common.handler;

import csu.yulin.springboot3template.common.response.ResultResponse;
import csu.yulin.springboot3template.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     *
     * @param e 业务异常对象
     * @return 返回封装的结果响应
     */
    @ExceptionHandler(BusinessException.class)
    public ResultResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException occurred", e);
        return ResultResponse.failure(e.getResultCode());
    }
}

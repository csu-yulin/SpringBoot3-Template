package csu.yulin.springboot3template.exception;


import csu.yulin.springboot3template.enums.ResultCode;
import lombok.Getter;

/**
 * 业务异常类
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final ResultCode resultCode;


    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}

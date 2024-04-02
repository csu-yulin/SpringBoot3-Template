package csu.yulin.springboot3template.utils;


import csu.yulin.springboot3template.enums.ResultCode;
import csu.yulin.springboot3template.exception.BusinessException;

/**
 * 异常抛出工具类，用于根据条件抛出指定异常。
 */
public class ThrowUtils {

    /**
     * 根据条件抛出运行时异常
     *
     * @param condition        条件表达式，如果为true，则抛出异常
     * @param runtimeException 要抛出的运行时异常对象
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 根据条件抛出业务异常
     *
     * @param condition  条件表达式，如果为true，则抛出异常
     * @param resultCode 业务异常的结果代码
     */
    public static void throwIf(boolean condition, ResultCode resultCode) {
        throwIf(condition, new BusinessException(resultCode));
    }
}

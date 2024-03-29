package csu.yulin.springboot3template.common;

import lombok.AllArgsConstructor;

/**
 * 响应结果枚举，定义了常见的响应结果和对应的响应码和错误描述。
 */
@AllArgsConstructor
public enum ResultCode implements BaseResultInterface {
    SUCCESS(200, "成功"),//成功
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "认证失败"),//未认证
    NOT_FOUND(404, "接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR(500, "系统繁忙"),//服务器内部错误
    METHOD_NOT_ALLOWED(405, "方法不被允许"),

    /*参数错误:1001-1999*/
    PARAMS_IS_INVALID(1001, "参数无效"),
    PARAMS_IS_BLANK(1002, "参数为空");

    /*用户错误2001-2999*/

    // 响应码
    private final Integer code;

    // 错误描述
    private final String message;

    @Override
    public String getResultCode() {
        return this.code.toString();
    }

    @Override
    public String getResultMsg() {
        return this.message;
    }
}
package csu.yulin.springboot3template.enums;

import csu.yulin.springboot3template.common.response.BaseResultInterface;
import lombok.AllArgsConstructor;

/**
 * 响应结果枚举，定义了常见的响应结果和对应的响应码和错误描述。
 */
@AllArgsConstructor
public enum ResultCode implements BaseResultInterface {
    /* 成功：200 */
    SUCCESS(200, "成功"),

    /* 请求错误：400-499 */
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "认证失败"), // 未认证
    FORBIDDEN(403, "权限不足"), // 禁止访问
    NOT_FOUND(404, "接口不存在"), // 接口不存在
    METHOD_NOT_ALLOWED(405, "方法不被允许"),

    /* 服务器错误：500-599 */
    INTERNAL_SERVER_ERROR(500, "系统内部错误"), // 服务器内部错误
    SERVICE_UNAVAILABLE(503, "服务不可用"), // 服务暂时不可用

    /* 参数错误：1000-1999 */
    PARAMS_IS_INVALID(1001, "参数无效"),
    PARAMS_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"), // 参数类型错误
    PARAM_NOT_COMPLETE(1004, "参数缺失"), // 参数缺失

    /* 用户错误：2000-2999 */
    USER_NOT_LOGGED_IN(2001, "用户未登录"), // 用户未登录
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"), // 账号不存在或密码错误
    USER_ACCOUNT_EXPIRED(2003, "账号已过期"), // 账号已过期
    USER_ACCOUNT_LOCKED(2004, "账号被锁定"), // 账号被锁定
    USER_ACCOUNT_DISABLED(2005, "账号被禁用"), // 账号被禁用
    USER_ACCOUNT_NOT_EXIST(2006, "账号不存在"), // 账号不存在
    USER_ACCOUNT_ALREADY_EXIST(2007, "账号已存在"), // 账号已存在
    USER_ACCOUNT_USE_BY_OTHERS(2008, "账号下线"), // 账号在其他设备登录
    USER_TOKEN_EXPIRED(2009, "Token已过期"), // Token已过期
    USER_TOKEN_INVALID(2010, "Token无效"), // Token无效
    USER_NO_PERMISSION(2011, "没有访问权限"), // 没有访问权限
    USER_ROLE_UNKNOWN(2012, "未知的用户身份"), // 未知的用户身份

    /* 业务错误：3000-3999 */
    OPERATION_FAILURE(3001, "操作失败"), // 操作失败

    /* 其他错误：4000-4999 */
    CODE_NOT_EXIST(4001, "编码不存在"), // 编码不存在

    /* 第三方错误：5000-5999 */
    THIRD_PARTY_ERROR(5001, "第三方服务错误"), // 第三方服务错误
    THIRD_PARTY_TIMEOUT(5002, "第三方服务超时"), // 第三方服务超时

    /* 文件上传：6000-6999 */
    FILE_UPLOAD_ERROR(6001, "文件上传失败"), // 文件上传失败
    FILE_DOWNLOAD_ERROR(6002, "文件下载失败"), // 文件下载失败

    /* 网络错误：7000-7999 */
    NETWORK_ERROR(7001, "网络异常"), // 网络异常
    NETWORK_TIMEOUT(7002, "网络超时"),// 网络超时

    /* 数据库相关错误：8000-8999 */
    DATA_NOT_FOUND(8002, "数据未找到"); // 数据未找到

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
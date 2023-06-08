package org.lwd.microservice.boot.core.constant;

/**
 * <p>
 * 通用返回状态码
 * 成功统一返回1
 * 系统错误统一返回0
 * 业务错误返回错误码
 * 错误码定义：
 * 0-1000 为标准通用错误码，业务系统禁止使用
 * <p>
 * 错误码范围由于领域未固定和应用未设计暂时无法确定数量，后续补充
 *
 * @author lwd
 */
public enum HttpStatusEnum {

    /**
     * 1 请求成功
     */
    REQUEST_SUCCESS(1, "请求成功"),
    /**
     * 0 系统内部错误,请稍后重试
     */
    REQUEST_FAIL(0, "系统内部错误,请稍后重试"),

    NULL_REQUESTPARAM(101, "必填参数为空"),
    /**
     * 200 操作成功
     */
    SUCCESS(200, "操作成功"),
    CREATED(201, "创建成功"),
    ACCEPTED(202, "请求已接受"),
    NO_CONTENT(204, "操作已执行,无数据返回"),
    MOVED_PERM(301, "资源被移除"),
    SEE_OTHER(303, "请求重定向"),
    NOT_MODIFIED(304, "资源未修改"),
    BAD_REQUEST(400, "参数列表错误,错误请求"),
    UNAUTHORIZED(401, "未授权，无权访问或操作"),
    FORBIDDEN(403, "访问授权过期,请重新登录"),
    NOT_FOUND(404, "资源，服务未找到"),
    BAD_METHOD(405, "不允许的http方法,请求方法有误"),
    CONFLICT(409, "资源冲突，或者资源被锁"),
    UNSUPPORTED_TYPE(415, "不支持的数据，媒体类型"),


    /**
     * 500 系统错误
     */
    ERROR(500, "系统错误"),
    NOT_IMPLEMENTED(501, "接口未实现"),
    UNAVAILABLE(503, "服务不可用"),
    COUNT_NOT_ALL_COMFIRM(504, "清点未全部提交"),

    ;

    int code;
    String message;

    HttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static int isSuccess(Integer code) {
        if (code == null || code == REQUEST_SUCCESS.getCode() || code == SUCCESS.getCode()) {
            return 1;
        }
        return 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

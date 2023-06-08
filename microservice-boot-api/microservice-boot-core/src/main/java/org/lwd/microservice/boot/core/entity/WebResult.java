package org.lwd.microservice.boot.core.entity;


import org.lwd.microservice.boot.core.constant.HttpStatusEnum;

/**
 * API通用接口返回对象
 *
 * @author lwd
 */
public class WebResult<T> {
    /**
     * 返回状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    protected WebResult() {
    }

    protected WebResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static WebResult success() {
        return WebResult.success(HttpStatusEnum.REQUEST_SUCCESS);
    }


    public static WebResult success(HttpStatusEnum httpStatus) {
        return WebResult.success(httpStatus.getCode(), httpStatus.getMessage(), null);
    }


    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static WebResult success(String msg) {
        return WebResult.success(null, msg);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> WebResult<T> success(T data) {
        return new WebResult<T>(HttpStatusEnum.REQUEST_SUCCESS.getCode(), HttpStatusEnum.REQUEST_SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> WebResult<T> success(T data, String message) {
        return new WebResult<T>(HttpStatusEnum.REQUEST_SUCCESS.getCode(), message, data);
    }

    /**
     * 成功返回结果
     *
     * @param code    错误码
     * @param message 提示信息
     * @param data    获取的数据
     */
    public static <T> WebResult<T> success(int code, String message, T data) {
        return new WebResult<T>(code, message, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static <T> WebResult<T> error() {
        return WebResult.error(HttpStatusEnum.REQUEST_FAIL.getMessage());
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static <T> WebResult<T> error(HttpStatusEnum httpStatusEnum) {
        return WebResult.error(httpStatusEnum.getCode(), httpStatusEnum.getMessage());
    }


    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> WebResult<T> error(String msg) {
        return WebResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> WebResult<T> error(String msg, T data) {
        return new WebResult(HttpStatusEnum.REQUEST_FAIL.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static <T> WebResult<T> error(int code, String msg) {
        return new WebResult(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        if (this.code == HttpStatusEnum.REQUEST_SUCCESS.getCode()) {
            return HttpStatusEnum.REQUEST_SUCCESS.getMessage();
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package org.lwd.microservice.boot.core.entity;


import org.lwd.microservice.boot.core.constant.HttpStatusEnum;

import java.io.Serializable;

/**
 * RPC服务返回结构体,API接口禁止使用
 *
 * @author lwd
 * @since 20230605
 */
public class BaseResult<T> implements Serializable {

    private static final Long serialVersionUID = 1L;

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

    private BaseResult() {
    }

    private BaseResult(int code) {
        this.code = code;
    }

    private BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private BaseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResult<T> success(BaseResult<T> data) {
        return restResult(data.getData(), data.getCode(), data.getMessage());
    }

    public static <T> BaseResult<T> success() {
        return restResult(null, HttpStatusEnum.REQUEST_SUCCESS.getCode(), HttpStatusEnum.REQUEST_SUCCESS.getMessage());
    }

    public static <T> BaseResult<T> success(T data) {
        return restResult(data, HttpStatusEnum.REQUEST_SUCCESS.getCode(), HttpStatusEnum.REQUEST_SUCCESS.getMessage());
    }

    public static <T> BaseResult<T> success(T data, String msg) {
        return restResult(data, HttpStatusEnum.REQUEST_SUCCESS.getCode(), msg);
    }

    public static <T> BaseResult<T> error() {
        return restResult(null, HttpStatusEnum.REQUEST_FAIL.getCode(), HttpStatusEnum.REQUEST_FAIL.getMessage());
    }

    public static <T> BaseResult<T> error(String msg) {
        return restResult(null, HttpStatusEnum.REQUEST_FAIL.getCode(), msg);
    }

    public static <T> BaseResult<T> error(T data) {
        return restResult(data, HttpStatusEnum.REQUEST_FAIL.getCode(), HttpStatusEnum.REQUEST_FAIL.getMessage());
    }

    public static <T> BaseResult<T> error(HttpStatusEnum httpStatusEnum) {
        return restResult(null, httpStatusEnum.getCode(), httpStatusEnum.getMessage());
    }

    public static <T> BaseResult<T> error(T data, String msg) {
        return restResult(data, HttpStatusEnum.REQUEST_FAIL.getCode(), msg);
    }

    public static <T> BaseResult<T> error(Integer code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> BaseResult<T> restResult(T data, int code, String msg) {
        BaseResult<T> apiResult = new BaseResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }


    @SuppressWarnings("rawtypes")
    public static BaseResult build(int code, String message) {
        return new BaseResult(code, message);
    }

    public static <T> BaseResult<T> build(int code, String message, T data) {
        return new BaseResult<T>(code, message, data);
    }

    /**
     * true:成功 ,false:不成功
     */
    public boolean isSuccess() {
        boolean b = (this.code == HttpStatusEnum.REQUEST_SUCCESS.getCode() || this.code == HttpStatusEnum.SUCCESS.getCode() || (this.code >= 200 && this.code < 300));
        if (!b) {
            this.data = null;
        }
        return b;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package org.lwd.microservice.boot.core.constant;

/**
 * 核心常量
 *
 * @author lwd
 * @since  2023/6/13
 * @version  1.0.0
 */
public class CoreConstant {
    /**
     * 请求头-登录来源的key
     */
    public static final String LOGIN_CLIENT_KEY = "clientKey";
    public static final String TOKEN = "token";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String IGNORE = "Ignore";
    public static final String TRACE_ID = "traceId";
    public static final String USER_ID = "userId";
    public static final String MERCHANT_ID = "merchantId";
    public static final String MERCHANT_NAME = "merchantName";
    public static final String APP_ID = "appId";
    public static final String APP_NAME = "appName";

    public static final String CONTEXT_ID = "contextId";
    //========================================接口相关=========================================
    public static final String API_APP = "/app/{appId}";
    public static final String API_FORM = "/form/{formId}";
    public static final String API_APP_FORM = "/app/{appId}/form/{formId}";
    public static final String API_PATH_APPID = "appId";
    public static final String API_PATH_FORMID = "formId";


    //========================================权限相关=========================================

}

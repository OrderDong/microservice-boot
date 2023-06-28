package org.lwd.microservice.boot.core.constant;

/**
 * Filter的顺序常量，执行顺序统一管理
 *
 * @author llwd
 * @since  2023/6/23
 * @version  1.0.0
 */
public class FilterOrderConstant {

    /**
     * 最小
     */
    public static final int MIN_FILTER = Integer.MIN_VALUE;

    /**
     * 日志，连路追踪等
     */
    public static final int LOG_FILTER = MIN_FILTER + 1;

    /**
     * 0
     */
    public static final int ZERO_FILTER = 0;

    /**
     * 多租户
     */
    public static final int MULTITENANT_FILTER = ZERO_FILTER + 1;

    /**
     * 请求头必须大于-105
     *
     * @see org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter
     */
    public static final int HEADERS_FILTER = MULTITENANT_FILTER + 1;

    /**
     * 最大
     */
    public static final int MAX_FILTER = Integer.MAX_VALUE;


}

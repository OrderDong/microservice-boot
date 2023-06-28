package org.lwd.microservice.boot.core.utils;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Map;

/**
 * 异步请求头holder
 *
 * @author virtiL
 * @date 2022/11/2
 * @see
 * @since 1.0.0.0
 */
public class AsyncRequestHeadersHolder {
    public static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static Map<String, String> getHeaderMap() {
        return THREAD_LOCAL.get();
    }

    public static void setHeaderMap(Map<String, String> headerMap) {
        THREAD_LOCAL.set(headerMap);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}

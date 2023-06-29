package org.lwd.microservice.boot.middle.log.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.lwd.microservice.boot.core.constant.CoreConstant;
import org.slf4j.MDC;

/**
 * log日志工具类
 *
 * @author lwd
 * @since 2023/6/12 21:27
 */
@Slf4j
public class LogContextUtils {

    /**
     * 记录操作内容
     */
    private static final ThreadLocal<String> logContext = new TransmittableThreadLocal<>();

    /**
     * 设置操作内容明细
     *
     * @param content
     */
    public static void setLogContent(String content) {
        if (StrUtil.isNotBlank(content)) {
            String exist = logContext.get();
            if (StrUtil.isNotBlank(exist)) {
                content = exist + "," + content;
            }
            logContext.set(content);
            //MDC.put(CONTEXT_ID,content);
        }
    }

    /**
     * 获取操作内容明细
     *
     * @return
     */
    public static String getLogContent() {
        //MDC.get(CONTEXT_ID);
        return logContext.get();
    }

    /**
     * 从MDC获取TraceId
     *
     * @return
     */
    public static String getTraceId() {
        if (MDC.get(CoreConstant.TRACE_ID) == null) {
            return IdUtil.fastSimpleUUID().toUpperCase();
        } else {
            return MDC.get(CoreConstant.TRACE_ID);
        }
    }

    /**
     * 将TraceId设置到MDC
     */
    public static void setTraceId(String traceId) {
        //分布式链路追踪的话可以替换成SkyWalking的TraceContext.traceId()或者用MDC的traceId
        MDC.put(CoreConstant.TRACE_ID, StrUtil.isNotBlank(traceId) ? traceId : IdUtil.fastSimpleUUID().toUpperCase());
    }

    /**
     * 从MDC删除TraceId
     */
    public static void removeTraceId() {
        MDC.remove(CoreConstant.TRACE_ID);
    }

    /**
     * 删除MDC缓存信息
     */
    public static void removeMDC() {
        MDC.remove(CoreConstant.TRACE_ID);
        MDC.remove(CoreConstant.CONTEXT_ID);
        logContext.remove();
    }

}

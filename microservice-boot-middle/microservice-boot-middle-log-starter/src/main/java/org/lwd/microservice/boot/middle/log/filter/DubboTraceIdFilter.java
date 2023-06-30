package org.lwd.microservice.boot.middle.log.filter;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.lwd.microservice.boot.core.constant.CoreConstant;
import org.slf4j.MDC;

/**
 * traceId过滤器
 *
 * @author lwd
 */
@Slf4j
@Activate(group = {CommonConstants.PROVIDER})
public class DubboTraceIdFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        RpcContext rpcContext = RpcContext.getContext();
        Result result = null;
        try {
            // traceId来源 web容器设置、非web容器
            String traceId = rpcContext.getAttachment(CoreConstant.TRACE_ID);
            if (traceId == null) {
                //通过MDC获取上下文中的traceId
                if (MDC.get(CoreConstant.TRACE_ID) != null) {
                    traceId = MDC.get(CoreConstant.TRACE_ID);
                } else {
                    //重新生成
                    String uuid = IdUtil.fastSimpleUUID().toUpperCase();
                    MDC.put(CoreConstant.TRACE_ID, uuid);
                }
            }
            String userId = rpcContext.getAttachment(CoreConstant.USER_ID);
            if (userId == null) {
                if (MDC.get(CoreConstant.USER_ID) != null) {
                    userId = MDC.get(CoreConstant.USER_ID);
                } else {
                    userId = "";
                }
            }
            if (log.isDebugEnabled()) {
                log.debug("TraceIdFilter traceId:{} userId:{}", traceId, userId);
            }
            //设置附加参数
            rpcContext.setAttachment(CoreConstant.TRACE_ID, traceId);
            rpcContext.setAttachment(CoreConstant.USER_ID, userId);
            //设置mdc 用于日志打印
            MDC.put(CoreConstant.TRACE_ID, traceId);
            MDC.put(CoreConstant.USER_ID, userId);
            result = invoker.invoke(invocation);
            if (rpcContext.isConsumerSide()) {
                log.debug(" consumer traceId:{} userId:{}", traceId, userId);
            }
            if (rpcContext.isProviderSide()) {
                log.debug(" provider traceId:{} userId:{}", traceId, userId);
            }
        } finally {
            if (rpcContext.isProviderSide()) {
                //provider端调用完毕收移除mdc值 server端保留
                MDC.remove(CoreConstant.TRACE_ID);
                MDC.remove(CoreConstant.USER_ID);
                if (log.isDebugEnabled()) {
                    log.debug("TraceIdFilter traceId and userId clear");
                }
            }
        }
        return result;
    }
}

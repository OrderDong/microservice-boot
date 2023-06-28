package org.lwd.microservice.boot.middle.log.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.lwd.microservice.boot.core.constant.CoreConstant;
import org.lwd.microservice.boot.core.constant.FilterOrderConstant;
import org.lwd.microservice.boot.middle.log.utils.LogContextUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * traceId过滤器
 *
 * @author: lwd
 * @since 2023/06/25
 */
@Slf4j
@Component
@Order(FilterOrderConstant.LOG_FILTER)
public class WebTraceIdFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        //这里set后必须在后续操作中删除，不然会导致内存泄漏
        try {
            String traceId = request.getHeader(CoreConstant.TRACE_ID);
            if (StrUtil.isBlank(traceId)) {
                traceId = LogContextUtils.getTraceId();
            }
            LogContextUtils.setTraceId(traceId);
            String logContext = request.getHeader(CoreConstant.CONTEXT_ID);
            if (StrUtil.isNotBlank(logContext)) {
                LogContextUtils.setLogContent(logContext);
            }
            //继续执行
            filterChain.doFilter(request, response);
        } finally {
            //清除MDC
            LogContextUtils.removeMDC();
        }


    }


}

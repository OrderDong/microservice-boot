package org.lwd.microservice.boot.common.interceptor;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 动态数据源-拦截器模式-需要自定义规则
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/16
 */
@Slf4j
public class DynamicDataSourceChangeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        //String url = request.getRequestURL().toString();
        String[] uriArr = uri.split("/");
        if (uriArr[uriArr.length - 1].equals("detail")) {
            log.info("interceptor datasource1------");
            DynamicDataSourceContextHolder.push("master");
        } else if (uriArr[uriArr.length - 1].equals("detail2")) {
            log.info("interceptor datasource2------");
            DynamicDataSourceContextHolder.push("slave_1");
        } else {
            log.info("interceptor datasource dynamic 3------");
            DynamicDataSourceContextHolder.push("tenant_1");
        }
        return true;
    }
}

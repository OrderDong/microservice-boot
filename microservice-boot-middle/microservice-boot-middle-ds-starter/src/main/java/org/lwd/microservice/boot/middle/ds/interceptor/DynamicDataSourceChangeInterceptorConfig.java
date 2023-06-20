package org.lwd.microservice.boot.middle.ds.interceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc拦截器-自定义拦截路径
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/16
 */
//@Configuration
public class DynamicDataSourceChangeInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //如果拦截全部可以设置为 /**
        String[] path = {"/tenantDataSource/**"};
        //不需要拦截的接口路径
        String[] excludePath = {};
        DynamicDataSourceChangeInterceptor dynamicDataSourceChangeInterceptor = new DynamicDataSourceChangeInterceptor();
        registry.addInterceptor(dynamicDataSourceChangeInterceptor).addPathPatterns(path).excludePathPatterns(excludePath);
    }
}

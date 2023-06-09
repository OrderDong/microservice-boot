package org.lwd.microservice.boot.middle.ds.aop;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.lwd.microservice.boot.core.constant.DataSourceConstant;
import org.lwd.microservice.boot.middle.ds.provider.DataSourceChangeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据源拦截-aop模式
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/13
 */
@Order(1)
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
@Slf4j
public class DataSourceChangeAdvisor {

    @Autowired
    @SuppressWarnings("all")
    DataSourceChangeProvider dataSourceChangeProvider;

    /**
     * 按需设置需要切换的模式
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void pointDataSource() {
    }

    /**
     * 需要定义模块的规则
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointDataSource()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //获取当前请求对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String uri = request.getRequestURI();
                //String url = request.getRequestURL().toString();
                String[] uriArr = uri.split("/");
                //测试切换数据源去掉
                /*if (uriArr[uriArr.length - 1].equals("detail")) {
                    log.info("datasource1------");
                    changeDataSource("master");
                } else if (uriArr[uriArr.length - 1].equals("detail2")) {
                    log.info("datasource2------");
                    changeDataSource("slave_1");
                } else {
                    log.info("datasource dynamic 3------");
                    changeDataSource(DataSourceConstant.TENANT_SOURCE_HEADER + "1");
                }*/
            }
        } catch (Exception e) {
            log.error("日志拦截异常", e);
        } finally {
            return joinPoint.proceed();
        }
    }

    /**
     * 切换数据源
     *
     * @param dsName 数据源名称
     * @return
     */
    private void changeDataSource(String dsName) {
        String currentDsName = DynamicDataSourceContextHolder.peek();
        if (dsName.equals(currentDsName)) {
            log.info("当前数据源为:{},无需切换", currentDsName);
            return;
        }

        if (!DataSourceConstant.YAML_DATASOURCE_LIST.contains(dsName)) {
            dataSourceChangeProvider.checkDataSourceAndLoad(dsName);
        }
        DynamicDataSourceContextHolder.push(dsName);
    }

}

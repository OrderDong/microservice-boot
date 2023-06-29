package org.lwd.microservice.boot.middle.log.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.lwd.microservice.boot.middle.log.entity.LogConsoleTypeEnum;
import org.lwd.microservice.boot.middle.log.utils.LogUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 系统服务日志
 *
 * @author lwd
 * @since 2023/06/20
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DubboServiceLogAspect {


    /**
     * 服务请求的拦截和处理
     *
     * @param joinPoint 目标地址
     * @return Object
     * @throws Throwable
     */
    @Around("@within(org.apache.dubbo.config.annotation.DubboService) ")
    public Object doServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Date startTime = new Date();
        Object result = null;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            LogUtils.doLog(LogConsoleTypeEnum.DUBBO_SERVICE, joinPoint, null, startTime, result, null);
            return result;
        } catch (Throwable exception) {
            LogUtils.doLog(LogConsoleTypeEnum.DUBBO_SERVICE, joinPoint, null, startTime, null, exception);
            throw exception;
        }
    }

}

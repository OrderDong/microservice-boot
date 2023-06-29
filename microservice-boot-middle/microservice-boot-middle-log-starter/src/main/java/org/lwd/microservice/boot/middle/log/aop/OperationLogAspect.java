package org.lwd.microservice.boot.middle.log.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.lwd.microservice.boot.middle.log.annotation.OperationLog;
import org.lwd.microservice.boot.middle.log.entity.LogConsoleTypeEnum;
import org.lwd.microservice.boot.middle.log.utils.LogUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 切面，日志拦截的逻辑
 *
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/21
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OperationLogAspect {

    @Around("@annotation(operateLog)")
    public Object around(ProceedingJoinPoint joinPoint, OperationLog operateLog) throws Throwable {
        if (operateLog == null) {
            operateLog = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(OperationLog.class);
        }
        Date startTime = new Date();
        Object result = null;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            LogUtils.doLog(LogConsoleTypeEnum.API, joinPoint, operateLog, startTime, result, null);
            return result;
        } catch (Throwable exception) {
            LogUtils.doLog(LogConsoleTypeEnum.API, joinPoint, operateLog, startTime, null, exception);
            throw exception;
        }
    }
/*
    @Pointcut("@annotation(org.lwd.microservice.boot.middle.log.annotation.OperationLogInterceptor)")
    public void logInterceptorPointcut() {
    }

    @Before("logInterceptorPointcut()")
    public void beforeLog(JoinPoint joinPoint) {
        // 在方法执行前执行的逻辑
        log.info("Before logging...");
    }

    @After("logInterceptorPointcut()")
    public void afterLog(JoinPoint joinPoint) {
        // 在方法执行后执行的逻辑
        log.info("After logging...");
    }

    @AfterReturning(pointcut = "logInterceptorPointcut()", returning = "result")
    public void afterReturningLog(JoinPoint joinPoint, Object result) {
        // 在方法返回结果后执行的逻辑
        log.info("After returning logging...");
    }

    @AfterThrowing(pointcut = "logInterceptorPointcut()", throwing = "exception")
    public void afterThrowingLog(JoinPoint joinPoint, Throwable exception) {
        // 在方法抛出异常后执行的逻辑
        log.info("After throwing logging...");
    }*/
}

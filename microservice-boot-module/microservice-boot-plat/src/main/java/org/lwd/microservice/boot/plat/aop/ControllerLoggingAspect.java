/*
package org.lwd.microservice.boot.plat.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

*/
/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/25
 *//*

@Slf4j
@Aspect
@Component
public class ControllerLoggingAspect {


    @Pointcut("execution(* org.lwd.microservice.boot.plat.controller.*.*(..))")
    public void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        // 获取目标方法的详细信息
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // 打印日志
        log.info("Before executing {} with args: {}", methodName, Arrays.toString(args));
    }

    @After("controllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        // 获取目标方法的详细信息
        String methodName = joinPoint.getSignature().getName();

        // 打印日志
        log.info("After executing {}", methodName);
    }
}
*/

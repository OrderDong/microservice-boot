package org.lwd.microservice.boot.middle.log.annotation;

import org.lwd.microservice.boot.middle.log.type.LogTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作参数定义
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {

    /**
     * 项目模块
     */
    String busModule() default "";

    /**
     * 操作项
     */
    String title() default "";

    /**
     * 操作内容
     */
    String context() default "";

    /**
     * 日志类型
     */
    LogTypeEnum logType() default LogTypeEnum.DEFAULT;

    /**
     * 是否保存到db
     */
    boolean saveDB() default true;
    /**
     * 是否异步记录日志
     */
    boolean async() default true;

    /**
     * 是否记录方法入参
     */
    boolean logParams() default true;

    /**
     * 是否记录方法出参
     */
    boolean logResult() default true;
}


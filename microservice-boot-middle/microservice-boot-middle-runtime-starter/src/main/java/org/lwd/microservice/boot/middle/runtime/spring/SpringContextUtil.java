package org.lwd.microservice.boot.middle.runtime.spring;


import cn.hutool.core.exceptions.UtilException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Spring 上下文工具类
 *
 * @author lwd
 * @date 2023/6/6
 */
@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationEventPublisher applicationEventPublisher;
    private static ApplicationContext applicationContext = null;

    /**
     * 获取应用上下文
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        checkBean(clazz);
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过class获取Bean.跳过检查
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBeanSkipCheck(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 检查bean
     *
     * @param clazz
     * @param <T>
     */
    private static <T> void checkBean(Class<T> clazz) {
        String[] beanNamesForType = getApplicationContext().getBeanNamesForType(clazz);
        if (beanNamesForType.length == 0) {
            throw new UtilException("这个Bean [" + clazz + "] 不存在.");
        }
        if (beanNamesForType.length > 1) {
            throw new UtilException("这个Bean [" + clazz + "] 存在多个.");
        }
    }

    /**
     * 获取getBeanDefinitionNames.
     *
     * @return getBeanDefinitionNames
     */
    public static List<String> getBeanDefinitionNames() {
        String[] names = getApplicationContext().getBeanDefinitionNames();
        return Arrays.asList(names);

    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        checkBean(clazz);
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 根据类型获取所有bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 获取用户信息
     *
     * @return
     */

    /**
     * 监听事件
     *
     * @param shiningEvent
     * @param <T>
     */
    public static <T> void publishShiningEvent(T shiningEvent) {
        if (applicationEventPublisher != null) {
            applicationEventPublisher.publishEvent(shiningEvent);
        }
    }

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        SpringContextUtil.applicationEventPublisher = applicationEventPublisher;
    }

}

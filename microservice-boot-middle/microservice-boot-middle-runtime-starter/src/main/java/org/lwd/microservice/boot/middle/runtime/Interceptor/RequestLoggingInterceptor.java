package org.lwd.microservice.boot.middle.runtime.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.stream.Collectors;

/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/21
 */
@Slf4j
public class RequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 在请求处理之前打印请求日志
        log.info("Request URL: " + request.getRequestURL());
        log.info("Request Method: " + request.getMethod());
        // 获取POST请求的内容
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // 打印请求内容日志
        log.info("POST请求内容：{}", requestBody);

        // 将请求内容存储在HttpServletRequest中，以便后续处理方法使用
        // 破坏了请求post体，不能这么使用
        request.setAttribute("requestBody", requestBody);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 请求处理之后调用，但在视图被渲染之前（Controller方法调用之后）

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

        // 整个请求完成之后调用，包括渲染视图和异常处理
    }

    private String getRequestHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headers = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.append(headerName).append(": ").append(headerValue).append(", ");
        }
        return headers.toString();
    }

    private String getRequestBody(HttpServletRequest request) {
        // 读取请求体内容并返回
        try (BufferedReader reader = request.getReader()) {
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            return body.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

package org.lwd.microservice.boot.middle.log.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.lwd.microservice.boot.core.constant.HttpStatusEnum;
import org.lwd.microservice.boot.core.entity.WebResult;
import org.lwd.microservice.boot.core.utils.RequestUtils;
import org.lwd.microservice.boot.middle.log.annotation.OperationLog;
import org.lwd.microservice.boot.middle.log.entity.LogConsoleTypeEnum;
import org.lwd.microservice.boot.middle.log.entity.OperationLogDTO;
import org.lwd.microservice.boot.middle.log.service.ModuleLogService;
import org.lwd.microservice.boot.middle.runtime.spring.SpringContextUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author lwd
 * @since 2023/6/23
 */

@Slf4j
public class LogUtils {

    /**
     * 记录日志
     *
     * @param joinPoint
     * @param operateLog
     * @param startTime
     * @param result
     * @param exception
     */

    public static void doLog(LogConsoleTypeEnum logConsoleTypeEnum, ProceedingJoinPoint joinPoint, OperationLog operateLog, Date startTime, Object result, Throwable exception) {
        Object target = joinPoint.getTarget();
        if (target instanceof ModuleLogService) {
            return;
        }
        OperationLogDTO operationLogDTO = new OperationLogDTO();
        // 填充TraceId
        operationLogDTO.setTraceId(LogContextUtils.getTraceId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(DateUtil.date());
        operationLogDTO.setStartTime(strDate);
        // 填充用户信息
        //setUserInfo(operationLogDTO);
        // 填充模块信息
        setModuleInfo(operationLogDTO, joinPoint, operateLog);
        // 填充请求信息
        setRequestInfo(operationLogDTO);
        // 填充方法信息
        setMethodInfo(operationLogDTO, joinPoint, operateLog, startTime, result, exception);
        if (exception != null) {
            //从控制台清理返回结果
            operationLogDTO.setResultData(null);
            printlnLog("Exception", operationLogDTO);
        }
        if (operateLog == null) {
            //从控制台清理返回结果
            operationLogDTO.setResultData(null);
            printlnLog(logConsoleTypeEnum.getName(), operationLogDTO);
        } else {
            //可按不同级别保存日志内容
            if (operateLog.saveDB() && logConsoleTypeEnum.getName().equals("API")) {
                ModuleLogService moduleLogService = SpringContextUtil.getBeanSkipCheck(ModuleLogService.class);
                if (moduleLogService != null) {
                    // 保存日志到db
                    if (operateLog.async()) {
                        moduleLogService.savePlatLogAsync(operationLogDTO);
                    } else {
                        moduleLogService.savePlatLog(operationLogDTO);
                    }
                    return;
                }
            }
            //从控制台清理返回结果
            operationLogDTO.setResultData(null);
            printlnLog(logConsoleTypeEnum.getName(), operationLogDTO);
        }
    }

    /**
     * 填充方法信息
     *
     * @param operationLogDTO
     * @param joinPoint
     * @param operateLog
     * @param startTime
     * @param result
     * @param exception
     */

    private static void setMethodInfo(OperationLogDTO operationLogDTO, ProceedingJoinPoint joinPoint, OperationLog operateLog, Date startTime, Object result, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        operationLogDTO.setJavaMethod(methodSignature.toString());
        if (operateLog == null) {
            operationLogDTO.setJavaMethodParams(RequestUtils.getMethodParams(joinPoint));
            if (result instanceof WebResult) {
                result = ((WebResult<?>) result).getData();
            }
            operationLogDTO.setResultData(JSON.toJSONString(result));
        } else {
            if (operateLog.logParams()) {
                operationLogDTO.setJavaMethodParams(RequestUtils.getMethodParams(joinPoint));
            }
            if (operateLog.logResult()) {
                if (result instanceof WebResult) {
                    result = ((WebResult<?>) result).getData();
                }
                operationLogDTO.setResultData(JSON.toJSONString(result));
            }
        }
        //计算执行时长
        operationLogDTO.setExecTime((int) (System.currentTimeMillis() - startTime.getTime()));
        if (result != null) {
            if (result instanceof WebResult) {
                WebResult<?> webResult = (WebResult<?>) result;
                operationLogDTO.setResultCode(webResult.getCode());
                operationLogDTO.setResultMsg(webResult.getMessage());
            } else {
                operationLogDTO.setResultCode(HttpStatusEnum.SUCCESS.getCode());
            }
        }
        // 异常
        if (exception != null) {
            operationLogDTO.setResultCode(HttpStatusEnum.ERROR.getCode());
            operationLogDTO.setResultMsg(ExceptionUtil.getRootCauseMessage(exception));
        }
        operationLogDTO.setResultCode(operationLogDTO.getResultCode() == null ? HttpStatusEnum.SUCCESS.getCode() : operationLogDTO.getResultCode());
    }

    /**
     * 填充请求信息
     *
     * @param operationLogDTO
     */

    private static void setRequestInfo(OperationLogDTO operationLogDTO) {
        HttpServletRequest request = RequestUtils.getRequest();
        if (request == null) {
            return;
        }
        operationLogDTO.setRequestMethod(request.getMethod());
        operationLogDTO.setRequestUrl(request.getRequestURI());
        operationLogDTO.setClientIp(RequestUtils.getClientIP(request));
        operationLogDTO.setUserAgent(RequestUtils.getUserAgent(request));
    }

    /**
     * 填充模块信息
     *
     * @param operationLogDTO
     * @param joinPoint
     * @param operateLog
     */

    private static void setModuleInfo(OperationLogDTO operationLogDTO, ProceedingJoinPoint joinPoint, OperationLog operateLog) {
        if (operateLog != null) {
            operationLogDTO.setBusModule(operateLog.busModule());
            operationLogDTO.setTitle(operateLog.title());
            if (operateLog.logType() != null) {
                operationLogDTO.setType(operateLog.logType().getType());
            }

            if (StrUtil.isNotBlank(LogContextUtils.getLogContent())) {
                operationLogDTO.setContent(LogContextUtils.getLogContent());
            }
        } else {
            if (StrUtil.isNotBlank(operationLogDTO.getRequestUrl())) {
                String requestUrl = operationLogDTO.getRequestUrl();
                String uri = requestUrl.substring(1);
                if (StrUtil.isBlank(uri)) {
                    return;
                }
                String[] split = uri.split("/");
                if (split.length > 2) {
                    operationLogDTO.setBusModule(split[0]);
                    operationLogDTO.setTitle(split[split.length - 1]);
                }
                if (split.length == 2) {
                    operationLogDTO.setBusModule(split[0]);
                    operationLogDTO.setTitle(split[1]);
                }
                if (split.length < 2) {
                    operationLogDTO.setBusModule(split[0]);
                    operationLogDTO.setTitle(split[0]);
                }
            }
            operationLogDTO.setType(-1);
            if (StrUtil.isNotBlank(LogContextUtils.getLogContent())) {
                operationLogDTO.setContent(LogContextUtils.getLogContent());
            }
        }
    }

    /**
     * 填充用户信息
     *
     * @param
     */

   /* private static void setUserInfo(OperationLogDTO operationLogDTO) {
        try {
            ShiningUserInfo userInfo = SpringContextUtil.getUserInfo();
            operationLogDTO.setLoginId(String.valueOf(userInfo.getUserLoginId()));
            operationLogDTO.setUserId(String.valueOf(userInfo.getUserId()));
            operationLogDTO.setMerchantId(userInfo.getMerchantId());
            operationLogDTO.setMerchantName(userInfo.getMerchantName());
            operationLogDTO.setUserName(userInfo.getUserName());
            operationLogDTO.setUserMobile(userInfo.getMobile());
        } catch (Exception e) {
            // 不做处理
        }
    }
*/
    private static void printlnLog(String type, OperationLogDTO dto) {
        String traceId = dto.getTraceId();
        dto.setTraceId(null);
        String userId = dto.getUserId();
        dto.setUserId(null);
        Integer tenantId = dto.getTenantId();
        dto.setTenantId(null);
        String requestUrl = dto.getRequestUrl();
        dto.setRequestUrl(null);
        String requestMethod = dto.getRequestMethod();
        dto.setRequestMethod(null);
        String title = dto.getTitle();
        dto.setTitle(null);
        String javaMethod = dto.getJavaMethod();
        dto.setJavaMethod(null);
        String methodParams = dto.getJavaMethodParams();
        dto.setJavaMethodParams(null);
        String clientIp = dto.getClientIp();
        dto.setClientIp(null);

        log.info("[" + type + "日志]: traceId:{} clientIp:{} userId:{} tenantId:{} requestUrl:{} requestMethod:{} title:{} javaMethod:{} methodParams:{} other:{}", traceId, clientIp, userId, tenantId, requestUrl, requestMethod, title, javaMethod, methodParams, JSON.toJSONString(dto));
    }
}

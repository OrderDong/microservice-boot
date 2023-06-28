package org.lwd.microservice.boot.middle.log.service;


import org.lwd.microservice.boot.middle.log.entity.OperationLogDTO;

import java.util.concurrent.Future;

/**
 * 日志保存拓展接口
 *
 * @author virtiL
 * @version 1.0.0.0
 * @time: 2022/6/12 22:30
 * @see
 */
public interface ModuleLogService {


    /**
     * 记录操作日志(异步)
     *
     * @param operateLogDTO 操作日志请求
     * @return true: 记录成功,false: 记录失败
     */
    Future<Boolean> savePlatLogAsync(OperationLogDTO operateLogDTO);

    /**
     * 记录平台日志(同步)
     *
     * @param operateLogDTO 操作日志请求
     * @return true: 记录成功,false: 记录失败
     */
    Boolean savePlatLog(OperationLogDTO operateLogDTO);


}

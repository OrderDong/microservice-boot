package org.lwd.microservice.boot.es.service;

import org.lwd.microservice.boot.es.entity.VisitLog;

import java.util.List;

/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/26
 */
public interface VisitLogService {
    /**
     *
     * @param visitLog
     * @return
     */
    VisitLog saveVisitLog(VisitLog visitLog);

    List<VisitLog> findAll();

    List<VisitLog> findByUserLoginId(Integer userLoginId);
}

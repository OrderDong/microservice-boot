package org.lwd.microservice.boot.es.service.impl;

import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RestClient;
import org.lwd.microservice.boot.es.dao.VisitLogRepository;
import org.lwd.microservice.boot.es.entity.VisitLog;
import org.lwd.microservice.boot.es.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/26
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {

    @Autowired
    private VisitLogRepository visitLogRepository;

    @Override
    public VisitLog saveVisitLog(VisitLog visitLog) {
        return visitLogRepository.save(visitLog);
    }

    @Override
    public List<VisitLog> findAll() {
        Iterable<VisitLog> visitLogs = visitLogRepository.findAll();
        List<VisitLog> logs = new ArrayList<>();
        visitLogs.forEach(visitLog -> logs.add(visitLog));
        return logs;
    }

    @Override
    public List<VisitLog> findByUserLoginId(Integer userLoginId) {
        return visitLogRepository.findByUserLoginId(userLoginId);
    }
}

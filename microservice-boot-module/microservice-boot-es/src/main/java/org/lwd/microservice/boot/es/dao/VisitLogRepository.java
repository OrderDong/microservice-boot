package org.lwd.microservice.boot.es.dao;

import org.lwd.microservice.boot.es.entity.VisitLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/26
 */
@Repository
public interface VisitLogRepository extends ElasticsearchRepository<VisitLog, String> {

    //自定义规则
    List<VisitLog> findByUserLoginId(Integer userLoginId);
}

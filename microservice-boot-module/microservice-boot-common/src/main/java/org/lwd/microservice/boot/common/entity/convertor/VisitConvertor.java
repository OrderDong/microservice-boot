package org.lwd.microservice.boot.common.entity.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.lwd.microservice.boot.core.convertor.AbstractConvertor;
import org.lwd.microservice.boot.common.entity.dto.VisitDTO;
import org.lwd.microservice.boot.common.entity.vo.VisitVO;
import org.lwd.microservice.boot.common.entity.Visit;

/**
* <p>
* 系统访问记录数据转换
* </p>
*
* @author lwd
* @since 2023-06-28
*/
@Mapper
public interface VisitConvertor extends AbstractConvertor<VisitVO, VisitDTO, Visit> {
    VisitConvertor INSTANCE = Mappers.getMapper(VisitConvertor.class);
}

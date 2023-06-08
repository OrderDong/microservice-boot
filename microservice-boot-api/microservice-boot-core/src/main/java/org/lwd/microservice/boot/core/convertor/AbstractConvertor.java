package org.lwd.microservice.boot.core.convertor;

public interface AbstractConvertor<VO, DTO, ENTITY> {

    VO toVO(ENTITY entity);

    ENTITY toEntity(DTO dto);

    DTO toDTO(ENTITY entity);

    VO dtoToVO(DTO dto);

}

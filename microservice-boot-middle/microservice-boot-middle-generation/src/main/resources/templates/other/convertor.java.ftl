package ${package.Entity}.convertor;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.lwd.microservice.boot.core.convertor.AbstractConvertor;
import ${package.Entity}.dto.${entity}DTO;
import ${package.Entity}.vo.${entity}VO;
import ${package.Entity}.${entity};

/**
* <p>
* ${table.comment!}数据转换
* </p>
*
* @author ${author}
* @since ${date}
*/
@Mapper
public interface ${entity}Convertor extends AbstractConvertor<${entity}VO, ${entity}DTO, ${entity}> {
    ${entity}Convertor INSTANCE = Mappers.getMapper(${entity}Convertor.class);
}

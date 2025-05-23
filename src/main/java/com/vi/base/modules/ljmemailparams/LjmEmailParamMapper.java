/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailparams;

import com.vi.model.dao.LjmEmailParamDAO;
import com.vi.model.dto.LjmEmailParamDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LjmEmailParamMapper {

	LjmEmailParamMapper INSTANCE = Mappers.getMapper(LjmEmailParamMapper.class);

	LjmEmailParamDTO ljmEmailParamDAOToLjmEmailParamDTO(LjmEmailParamDAO ljmEmailParamDAO);

	LjmEmailParamDAO ljmEmailParamDTOToLjmEmailParamDAO(LjmEmailParamDTO ljmEmailParamDTO);

	List<LjmEmailParamDTO> ljmEmailParamDAOListToLjmEmailParamDTOList(List<LjmEmailParamDAO> ljmEmailParamDAOList);

	List<LjmEmailParamDAO> ljmEmailParamDTOListToLjmEmailParamDAOList(List<LjmEmailParamDTO> ljmEmailParamDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(LjmEmailParamDTO dto, @MappingTarget LjmEmailParamDAO entity);
}
/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailtemplates;

import com.vi.model.dao.LjmEmailTemplateDAO;
import com.vi.model.dto.LjmEmailTemplateDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LjmEmailTemplateMapper {

	LjmEmailTemplateMapper INSTANCE = Mappers.getMapper(LjmEmailTemplateMapper.class);

	LjmEmailTemplateDTO ljmEmailTemplateDAOToLjmEmailTemplateDTO(LjmEmailTemplateDAO ljmEmailTemplateDAO);

	LjmEmailTemplateDAO ljmEmailTemplateDTOToLjmEmailTemplateDAO(LjmEmailTemplateDTO ljmEmailTemplateDTO);

	List<LjmEmailTemplateDTO> ljmEmailTemplateDAOListToLjmEmailTemplateDTOList(List<LjmEmailTemplateDAO> ljmEmailTemplateDAOList);

	List<LjmEmailTemplateDAO> ljmEmailTemplateDTOListToLjmEmailTemplateDAOList(List<LjmEmailTemplateDTO> ljmEmailTemplateDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(LjmEmailTemplateDTO dto, @MappingTarget LjmEmailTemplateDAO entity);
}
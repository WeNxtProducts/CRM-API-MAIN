/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.ljmemailtemplates;

import com.vi.model.dao.LjmEmailTemplateDAO;
import com.vi.model.dto.LjmEmailTemplateDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LjmEmailTemplateMapperCustom {

	LjmEmailTemplateMapperCustom INSTANCE = Mappers.getMapper(LjmEmailTemplateMapperCustom.class);

	LjmEmailTemplateDTOCustom ljmEmailTemplateDAOToLjmEmailTemplateDTOCustom(LjmEmailTemplateDAO ljmEmailTemplateDAO);

	LjmEmailTemplateDAO ljmEmailTemplateDTOCustomToLjmEmailTemplateDAO(LjmEmailTemplateDTOCustom ljmEmailTemplateDTOCustom);

	List<LjmEmailTemplateDTOCustom> ljmEmailTemplateDAOListToLjmEmailTemplateDTOListCustom(List<LjmEmailTemplateDAO> ljmEmailTemplateDAOList);

	List<LjmEmailTemplateDAO> ljmEmailTemplateDTOListCustomToLjmEmailTemplateDAOList(List<LjmEmailTemplateDTOCustom> ljmEmailTemplateDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(LjmEmailTemplateDTOCustom dto, @MappingTarget LjmEmailTemplateDAO entity);

}

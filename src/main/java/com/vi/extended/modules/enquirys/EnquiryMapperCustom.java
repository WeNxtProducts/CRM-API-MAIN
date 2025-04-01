/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.enquirys;

import com.vi.model.dao.EnquiryDAO;
import com.vi.model.dto.EnquiryDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EnquiryMapperCustom {

	EnquiryMapperCustom INSTANCE = Mappers.getMapper(EnquiryMapperCustom.class);

	EnquiryDTOCustom enquiryDAOToEnquiryDTOCustom(EnquiryDAO enquiryDAO);

	EnquiryDAO enquiryDTOCustomToEnquiryDAO(EnquiryDTOCustom enquiryDTOCustom);

	List<EnquiryDTOCustom> enquiryDAOListToEnquiryDTOListCustom(List<EnquiryDAO> enquiryDAOList);

	List<EnquiryDAO> enquiryDTOListCustomToEnquiryDAOList(List<EnquiryDTOCustom> enquiryDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(EnquiryDTOCustom dto, @MappingTarget EnquiryDAO entity);

}

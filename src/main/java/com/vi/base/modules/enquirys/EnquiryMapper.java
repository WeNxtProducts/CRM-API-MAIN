/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.enquirys;

import com.vi.model.dao.EnquiryDAO;
import com.vi.model.dto.EnquiryDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnquiryMapper {

	EnquiryMapper INSTANCE = Mappers.getMapper(EnquiryMapper.class);

	EnquiryDTO enquiryDAOToEnquiryDTO(EnquiryDAO enquiryDAO);

	EnquiryDAO enquiryDTOToEnquiryDAO(EnquiryDTO enquiryDTO);

	List<EnquiryDTO> enquiryDAOListToEnquiryDTOList(List<EnquiryDAO> enquiryDAOList);

	List<EnquiryDAO> enquiryDTOListToEnquiryDAOList(List<EnquiryDTO> enquiryDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(EnquiryDTO dto, @MappingTarget EnquiryDAO entity);
}
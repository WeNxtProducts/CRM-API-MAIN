/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.leads;

import com.vi.model.dao.LeadDAO;
import com.vi.model.dto.LeadDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeadMapper {

	LeadMapper INSTANCE = Mappers.getMapper(LeadMapper.class);

	LeadDTO leadDAOToLeadDTO(LeadDAO leadDAO);

	LeadDAO leadDTOToLeadDAO(LeadDTO leadDTO);

	List<LeadDTO> leadDAOListToLeadDTOList(List<LeadDAO> leadDAOList);

	List<LeadDAO> leadDTOListToLeadDAOList(List<LeadDTO> leadDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(LeadDTO dto, @MappingTarget LeadDAO entity);
}
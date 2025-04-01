/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.leads;

import com.vi.model.dao.LeadDAO;
import com.vi.model.dto.LeadDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LeadMapperCustom {

	LeadMapperCustom INSTANCE = Mappers.getMapper(LeadMapperCustom.class);

	LeadDTOCustom leadDAOToLeadDTOCustom(LeadDAO leadDAO);

	LeadDAO leadDTOCustomToLeadDAO(LeadDTOCustom leadDTOCustom);

	List<LeadDTOCustom> leadDAOListToLeadDTOListCustom(List<LeadDAO> leadDAOList);

	List<LeadDAO> leadDTOListCustomToLeadDAOList(List<LeadDTOCustom> leadDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(LeadDTOCustom dto, @MappingTarget LeadDAO entity);

}

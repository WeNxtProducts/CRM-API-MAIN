/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_part_comms;

import com.vi.model.dao.Pxt_fac_part_commDAO;
import com.vi.model.dto.Pxt_fac_part_commDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_part_commMapperCustom {

	Pxt_fac_part_commMapperCustom INSTANCE = Mappers.getMapper(Pxt_fac_part_commMapperCustom.class);

	Pxt_fac_part_commDTOCustom pxt_fac_part_commDAOToPxt_fac_part_commDTOCustom(Pxt_fac_part_commDAO pxt_fac_part_commDAO);

	Pxt_fac_part_commDAO pxt_fac_part_commDTOCustomToPxt_fac_part_commDAO(Pxt_fac_part_commDTOCustom pxt_fac_part_commDTOCustom);

	List<Pxt_fac_part_commDTOCustom> pxt_fac_part_commDAOListToPxt_fac_part_commDTOListCustom(List<Pxt_fac_part_commDAO> pxt_fac_part_commDAOList);

	List<Pxt_fac_part_commDAO> pxt_fac_part_commDTOListCustomToPxt_fac_part_commDAOList(List<Pxt_fac_part_commDTOCustom> pxt_fac_part_commDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(Pxt_fac_part_commDTOCustom dto, @MappingTarget Pxt_fac_part_commDAO entity);

}
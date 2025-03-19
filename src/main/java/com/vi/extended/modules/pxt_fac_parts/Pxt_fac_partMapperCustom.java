/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_parts;

import com.vi.model.dao.Pxt_fac_partDAO;
import com.vi.model.dto.Pxt_fac_partDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_partMapperCustom {

	Pxt_fac_partMapperCustom INSTANCE = Mappers.getMapper(Pxt_fac_partMapperCustom.class);

	Pxt_fac_partDTOCustom pxt_fac_partDAOToPxt_fac_partDTOCustom(Pxt_fac_partDAO pxt_fac_partDAO);

	Pxt_fac_partDAO pxt_fac_partDTOCustomToPxt_fac_partDAO(Pxt_fac_partDTOCustom pxt_fac_partDTOCustom);

	List<Pxt_fac_partDTOCustom> pxt_fac_partDAOListToPxt_fac_partDTOListCustom(List<Pxt_fac_partDAO> pxt_fac_partDAOList);

	List<Pxt_fac_partDAO> pxt_fac_partDTOListCustomToPxt_fac_partDAOList(List<Pxt_fac_partDTOCustom> pxt_fac_partDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(Pxt_fac_partDTOCustom dto, @MappingTarget Pxt_fac_partDAO entity);

}
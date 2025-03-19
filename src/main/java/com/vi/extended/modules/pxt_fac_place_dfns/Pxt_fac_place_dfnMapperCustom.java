/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_place_dfns;

import com.vi.model.dao.Pxt_fac_place_dfnDAO;
import com.vi.model.dto.Pxt_fac_place_dfnDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_place_dfnMapperCustom {

	Pxt_fac_place_dfnMapperCustom INSTANCE = Mappers.getMapper(Pxt_fac_place_dfnMapperCustom.class);

	Pxt_fac_place_dfnDTOCustom pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTOCustom(Pxt_fac_place_dfnDAO pxt_fac_place_dfnDAO);

	Pxt_fac_place_dfnDAO pxt_fac_place_dfnDTOCustomToPxt_fac_place_dfnDAO(Pxt_fac_place_dfnDTOCustom pxt_fac_place_dfnDTOCustom);

	List<Pxt_fac_place_dfnDTOCustom> pxt_fac_place_dfnDAOListToPxt_fac_place_dfnDTOListCustom(List<Pxt_fac_place_dfnDAO> pxt_fac_place_dfnDAOList);

	List<Pxt_fac_place_dfnDAO> pxt_fac_place_dfnDTOListCustomToPxt_fac_place_dfnDAOList(List<Pxt_fac_place_dfnDTOCustom> pxt_fac_place_dfnDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(Pxt_fac_place_dfnDTOCustom dto, @MappingTarget Pxt_fac_place_dfnDAO entity);

}
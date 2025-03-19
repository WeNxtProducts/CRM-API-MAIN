/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_hdrs;

import com.vi.model.dao.Pxt_fac_hdrDAO;
import com.vi.model.dto.Pxt_fac_hdrDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_hdrMapperCustom {

	Pxt_fac_hdrMapperCustom INSTANCE = Mappers.getMapper(Pxt_fac_hdrMapperCustom.class);

	Pxt_fac_hdrDTOCustom pxt_fac_hdrDAOToPxt_fac_hdrDTOCustom(Pxt_fac_hdrDAO pxt_fac_hdrDAO);

	Pxt_fac_hdrDAO pxt_fac_hdrDTOCustomToPxt_fac_hdrDAO(Pxt_fac_hdrDTOCustom pxt_fac_hdrDTOCustom);

	List<Pxt_fac_hdrDTOCustom> pxt_fac_hdrDAOListToPxt_fac_hdrDTOListCustom(List<Pxt_fac_hdrDAO> pxt_fac_hdrDAOList);

	List<Pxt_fac_hdrDAO> pxt_fac_hdrDTOListCustomToPxt_fac_hdrDAOList(List<Pxt_fac_hdrDTOCustom> pxt_fac_hdrDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(Pxt_fac_hdrDTOCustom dto, @MappingTarget Pxt_fac_hdrDAO entity);

}
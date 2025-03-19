/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_taxes;

import com.vi.model.dao.Pxt_fac_taxDAO;
import com.vi.model.dto.Pxt_fac_taxDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_taxMapperCustom {

	Pxt_fac_taxMapperCustom INSTANCE = Mappers.getMapper(Pxt_fac_taxMapperCustom.class);

	Pxt_fac_taxDTOCustom pxt_fac_taxDAOToPxt_fac_taxDTOCustom(Pxt_fac_taxDAO pxt_fac_taxDAO);

	Pxt_fac_taxDAO pxt_fac_taxDTOCustomToPxt_fac_taxDAO(Pxt_fac_taxDTOCustom pxt_fac_taxDTOCustom);

	List<Pxt_fac_taxDTOCustom> pxt_fac_taxDAOListToPxt_fac_taxDTOListCustom(List<Pxt_fac_taxDAO> pxt_fac_taxDAOList);

	List<Pxt_fac_taxDAO> pxt_fac_taxDTOListCustomToPxt_fac_taxDAOList(List<Pxt_fac_taxDTOCustom> pxt_fac_taxDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(Pxt_fac_taxDTOCustom dto, @MappingTarget Pxt_fac_taxDAO entity);

}
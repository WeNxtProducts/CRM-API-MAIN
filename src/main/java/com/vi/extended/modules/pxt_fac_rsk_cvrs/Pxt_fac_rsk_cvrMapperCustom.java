/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_rsk_cvrs;

import com.vi.model.dao.Pxt_fac_rsk_cvrDAO;
import com.vi.model.dto.Pxt_fac_rsk_cvrDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_rsk_cvrMapperCustom {

	Pxt_fac_rsk_cvrMapperCustom INSTANCE = Mappers.getMapper(Pxt_fac_rsk_cvrMapperCustom.class);

	Pxt_fac_rsk_cvrDTOCustom pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTOCustom(Pxt_fac_rsk_cvrDAO pxt_fac_rsk_cvrDAO);

	Pxt_fac_rsk_cvrDAO pxt_fac_rsk_cvrDTOCustomToPxt_fac_rsk_cvrDAO(Pxt_fac_rsk_cvrDTOCustom pxt_fac_rsk_cvrDTOCustom);

	List<Pxt_fac_rsk_cvrDTOCustom> pxt_fac_rsk_cvrDAOListToPxt_fac_rsk_cvrDTOListCustom(List<Pxt_fac_rsk_cvrDAO> pxt_fac_rsk_cvrDAOList);

	List<Pxt_fac_rsk_cvrDAO> pxt_fac_rsk_cvrDTOListCustomToPxt_fac_rsk_cvrDAOList(List<Pxt_fac_rsk_cvrDTOCustom> pxt_fac_rsk_cvrDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(Pxt_fac_rsk_cvrDTOCustom dto, @MappingTarget Pxt_fac_rsk_cvrDAO entity);

}
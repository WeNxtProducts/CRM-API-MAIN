/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_rsk_cvrs;

import com.vi.model.dao.Pxt_fac_rsk_cvrDAO;
import com.vi.model.dto.Pxt_fac_rsk_cvrDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_rsk_cvrMapper {

	Pxt_fac_rsk_cvrMapper INSTANCE = Mappers.getMapper(Pxt_fac_rsk_cvrMapper.class);

	Pxt_fac_rsk_cvrDTO pxt_fac_rsk_cvrDAOToPxt_fac_rsk_cvrDTO(Pxt_fac_rsk_cvrDAO pxt_fac_rsk_cvrDAO);

	Pxt_fac_rsk_cvrDAO pxt_fac_rsk_cvrDTOToPxt_fac_rsk_cvrDAO(Pxt_fac_rsk_cvrDTO pxt_fac_rsk_cvrDTO);

	List<Pxt_fac_rsk_cvrDTO> pxt_fac_rsk_cvrDAOListToPxt_fac_rsk_cvrDTOList(List<Pxt_fac_rsk_cvrDAO> pxt_fac_rsk_cvrDAOList);

	List<Pxt_fac_rsk_cvrDAO> pxt_fac_rsk_cvrDTOListToPxt_fac_rsk_cvrDAOList(List<Pxt_fac_rsk_cvrDTO> pxt_fac_rsk_cvrDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(Pxt_fac_rsk_cvrDTO dto, @MappingTarget Pxt_fac_rsk_cvrDAO entity);
}
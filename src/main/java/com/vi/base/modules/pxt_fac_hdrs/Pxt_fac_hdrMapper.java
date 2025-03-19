/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_hdrs;

import com.vi.model.dao.Pxt_fac_hdrDAO;
import com.vi.model.dto.Pxt_fac_hdrDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_hdrMapper {

	Pxt_fac_hdrMapper INSTANCE = Mappers.getMapper(Pxt_fac_hdrMapper.class);

	Pxt_fac_hdrDTO pxt_fac_hdrDAOToPxt_fac_hdrDTO(Pxt_fac_hdrDAO pxt_fac_hdrDAO);

	Pxt_fac_hdrDAO pxt_fac_hdrDTOToPxt_fac_hdrDAO(Pxt_fac_hdrDTO pxt_fac_hdrDTO);

	List<Pxt_fac_hdrDTO> pxt_fac_hdrDAOListToPxt_fac_hdrDTOList(List<Pxt_fac_hdrDAO> pxt_fac_hdrDAOList);

	List<Pxt_fac_hdrDAO> pxt_fac_hdrDTOListToPxt_fac_hdrDAOList(List<Pxt_fac_hdrDTO> pxt_fac_hdrDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(Pxt_fac_hdrDTO dto, @MappingTarget Pxt_fac_hdrDAO entity);
}
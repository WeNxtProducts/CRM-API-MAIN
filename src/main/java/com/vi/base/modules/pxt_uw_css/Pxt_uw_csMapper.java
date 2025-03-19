/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_uw_css;

import com.vi.model.dao.Pxt_uw_csDAO;
import com.vi.model.dto.Pxt_uw_csDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_uw_csMapper {

	Pxt_uw_csMapper INSTANCE = Mappers.getMapper(Pxt_uw_csMapper.class);

	Pxt_uw_csDTO pxt_uw_csDAOToPxt_uw_csDTO(Pxt_uw_csDAO pxt_uw_csDAO);

	Pxt_uw_csDAO pxt_uw_csDTOToPxt_uw_csDAO(Pxt_uw_csDTO pxt_uw_csDTO);

	List<Pxt_uw_csDTO> pxt_uw_csDAOListToPxt_uw_csDTOList(List<Pxt_uw_csDAO> pxt_uw_csDAOList);

	List<Pxt_uw_csDAO> pxt_uw_csDTOListToPxt_uw_csDAOList(List<Pxt_uw_csDTO> pxt_uw_csDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(Pxt_uw_csDTO dto, @MappingTarget Pxt_uw_csDAO entity);
}
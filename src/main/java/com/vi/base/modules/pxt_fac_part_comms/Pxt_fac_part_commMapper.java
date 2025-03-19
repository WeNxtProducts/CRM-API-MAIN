/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_part_comms;

import com.vi.model.dao.Pxt_fac_part_commDAO;
import com.vi.model.dto.Pxt_fac_part_commDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_part_commMapper {

	Pxt_fac_part_commMapper INSTANCE = Mappers.getMapper(Pxt_fac_part_commMapper.class);

	Pxt_fac_part_commDTO pxt_fac_part_commDAOToPxt_fac_part_commDTO(Pxt_fac_part_commDAO pxt_fac_part_commDAO);

	Pxt_fac_part_commDAO pxt_fac_part_commDTOToPxt_fac_part_commDAO(Pxt_fac_part_commDTO pxt_fac_part_commDTO);

	List<Pxt_fac_part_commDTO> pxt_fac_part_commDAOListToPxt_fac_part_commDTOList(List<Pxt_fac_part_commDAO> pxt_fac_part_commDAOList);

	List<Pxt_fac_part_commDAO> pxt_fac_part_commDTOListToPxt_fac_part_commDAOList(List<Pxt_fac_part_commDTO> pxt_fac_part_commDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(Pxt_fac_part_commDTO dto, @MappingTarget Pxt_fac_part_commDAO entity);
}
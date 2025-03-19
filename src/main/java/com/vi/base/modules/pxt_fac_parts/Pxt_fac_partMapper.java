/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_parts;

import com.vi.model.dao.Pxt_fac_partDAO;
import com.vi.model.dto.Pxt_fac_partDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_partMapper {

	Pxt_fac_partMapper INSTANCE = Mappers.getMapper(Pxt_fac_partMapper.class);

	Pxt_fac_partDTO pxt_fac_partDAOToPxt_fac_partDTO(Pxt_fac_partDAO pxt_fac_partDAO);

	Pxt_fac_partDAO pxt_fac_partDTOToPxt_fac_partDAO(Pxt_fac_partDTO pxt_fac_partDTO);

	List<Pxt_fac_partDTO> pxt_fac_partDAOListToPxt_fac_partDTOList(List<Pxt_fac_partDAO> pxt_fac_partDAOList);

	List<Pxt_fac_partDAO> pxt_fac_partDTOListToPxt_fac_partDAOList(List<Pxt_fac_partDTO> pxt_fac_partDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(Pxt_fac_partDTO dto, @MappingTarget Pxt_fac_partDAO entity);
}
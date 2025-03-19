/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_place_dfns;

import com.vi.model.dao.Pxt_fac_place_dfnDAO;
import com.vi.model.dto.Pxt_fac_place_dfnDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_place_dfnMapper {

	Pxt_fac_place_dfnMapper INSTANCE = Mappers.getMapper(Pxt_fac_place_dfnMapper.class);

	Pxt_fac_place_dfnDTO pxt_fac_place_dfnDAOToPxt_fac_place_dfnDTO(Pxt_fac_place_dfnDAO pxt_fac_place_dfnDAO);

	Pxt_fac_place_dfnDAO pxt_fac_place_dfnDTOToPxt_fac_place_dfnDAO(Pxt_fac_place_dfnDTO pxt_fac_place_dfnDTO);

	List<Pxt_fac_place_dfnDTO> pxt_fac_place_dfnDAOListToPxt_fac_place_dfnDTOList(List<Pxt_fac_place_dfnDAO> pxt_fac_place_dfnDAOList);

	List<Pxt_fac_place_dfnDAO> pxt_fac_place_dfnDTOListToPxt_fac_place_dfnDAOList(List<Pxt_fac_place_dfnDTO> pxt_fac_place_dfnDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(Pxt_fac_place_dfnDTO dto, @MappingTarget Pxt_fac_place_dfnDAO entity);
}
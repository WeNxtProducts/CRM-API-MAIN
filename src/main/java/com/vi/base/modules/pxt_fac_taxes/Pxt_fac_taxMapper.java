/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_taxes;

import com.vi.model.dao.Pxt_fac_taxDAO;
import com.vi.model.dto.Pxt_fac_taxDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Pxt_fac_taxMapper {

	Pxt_fac_taxMapper INSTANCE = Mappers.getMapper(Pxt_fac_taxMapper.class);

	Pxt_fac_taxDTO pxt_fac_taxDAOToPxt_fac_taxDTO(Pxt_fac_taxDAO pxt_fac_taxDAO);

	Pxt_fac_taxDAO pxt_fac_taxDTOToPxt_fac_taxDAO(Pxt_fac_taxDTO pxt_fac_taxDTO);

	List<Pxt_fac_taxDTO> pxt_fac_taxDAOListToPxt_fac_taxDTOList(List<Pxt_fac_taxDAO> pxt_fac_taxDAOList);

	List<Pxt_fac_taxDAO> pxt_fac_taxDTOListToPxt_fac_taxDAOList(List<Pxt_fac_taxDTO> pxt_fac_taxDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(Pxt_fac_taxDTO dto, @MappingTarget Pxt_fac_taxDAO entity);
}
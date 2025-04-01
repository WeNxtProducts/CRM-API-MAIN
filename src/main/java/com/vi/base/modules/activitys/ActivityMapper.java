/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitys;

import com.vi.model.dao.ActivityDAO;
import com.vi.model.dto.ActivityDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

	ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

	ActivityDTO activityDAOToActivityDTO(ActivityDAO activityDAO);

	ActivityDAO activityDTOToActivityDAO(ActivityDTO activityDTO);

	List<ActivityDTO> activityDAOListToActivityDTOList(List<ActivityDAO> activityDAOList);

	List<ActivityDAO> activityDTOListToActivityDAOList(List<ActivityDTO> activityDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(ActivityDTO dto, @MappingTarget ActivityDAO entity);
}
/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.activitylogs;

import com.vi.model.dao.ActivityLogDAO;
import com.vi.model.dto.ActivityLogDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityLogMapper {

	ActivityLogMapper INSTANCE = Mappers.getMapper(ActivityLogMapper.class);

	ActivityLogDTO activityLogDAOToActivityLogDTO(ActivityLogDAO activityLogDAO);

	ActivityLogDAO activityLogDTOToActivityLogDAO(ActivityLogDTO activityLogDTO);

	List<ActivityLogDTO> activityLogDAOListToActivityLogDTOList(List<ActivityLogDAO> activityLogDAOList);

	List<ActivityLogDAO> activityLogDTOListToActivityLogDAOList(List<ActivityLogDTO> activityLogDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(ActivityLogDTO dto, @MappingTarget ActivityLogDAO entity);
}
/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitylogs;

import com.vi.model.dao.ActivityLogDAO;
import com.vi.model.dto.ActivityLogDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityLogMapperCustom {

	ActivityLogMapperCustom INSTANCE = Mappers.getMapper(ActivityLogMapperCustom.class);

	ActivityLogDTOCustom activityLogDAOToActivityLogDTOCustom(ActivityLogDAO activityLogDAO);

	ActivityLogDAO activityLogDTOCustomToActivityLogDAO(ActivityLogDTOCustom activityLogDTOCustom);

	List<ActivityLogDTOCustom> activityLogDAOListToActivityLogDTOListCustom(List<ActivityLogDAO> activityLogDAOList);

	List<ActivityLogDAO> activityLogDTOListCustomToActivityLogDAOList(List<ActivityLogDTOCustom> activityLogDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(ActivityLogDTOCustom dto, @MappingTarget ActivityLogDAO entity);

}

/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitys;

import com.vi.model.dao.ActivityDAO;
import com.vi.model.dto.ActivityDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapperCustom {

	ActivityMapperCustom INSTANCE = Mappers.getMapper(ActivityMapperCustom.class);

	ActivityDTOCustom activityDAOToActivityDTOCustom(ActivityDAO activityDAO);

	ActivityDAO activityDTOCustomToActivityDAO(ActivityDTOCustom activityDTOCustom);

	List<ActivityDTOCustom> activityDAOListToActivityDTOListCustom(List<ActivityDAO> activityDAOList);

	List<ActivityDAO> activityDTOListCustomToActivityDAOList(List<ActivityDTOCustom> activityDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(ActivityDTOCustom dto, @MappingTarget ActivityDAO entity);

}

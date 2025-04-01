/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.fileattributes;

import com.vi.model.dao.FileAttributesDAO;
import com.vi.model.dto.FileAttributesDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FileAttributesMapperCustom {

	FileAttributesMapperCustom INSTANCE = Mappers.getMapper(FileAttributesMapperCustom.class);

	FileAttributesDTOCustom fileAttributesDAOToFileAttributesDTOCustom(FileAttributesDAO fileAttributesDAO);

	FileAttributesDAO fileAttributesDTOCustomToFileAttributesDAO(FileAttributesDTOCustom fileAttributesDTOCustom);

	List<FileAttributesDTOCustom> fileAttributesDAOListToFileAttributesDTOListCustom(List<FileAttributesDAO> fileAttributesDAOList);

	List<FileAttributesDAO> fileAttributesDTOListCustomToFileAttributesDAOList(List<FileAttributesDTOCustom> fileAttributesDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(FileAttributesDTOCustom dto, @MappingTarget FileAttributesDAO entity);

}

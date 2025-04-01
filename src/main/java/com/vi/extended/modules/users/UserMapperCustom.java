/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.users;

import com.vi.model.dao.UserDAO;
import com.vi.model.dto.UserDTOCustom;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapperCustom {

	UserMapperCustom INSTANCE = Mappers.getMapper(UserMapperCustom.class);

	UserDTOCustom userDAOToUserDTOCustom(UserDAO userDAO);

	UserDAO userDTOCustomToUserDAO(UserDTOCustom userDTOCustom);

	List<UserDTOCustom> userDAOListToUserDTOListCustom(List<UserDAO> userDAOList);

	List<UserDAO> userDTOListCustomToUserDAOList(List<UserDTOCustom> userDTOCustomList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValuesCustom(UserDTOCustom dto, @MappingTarget UserDAO entity);

}

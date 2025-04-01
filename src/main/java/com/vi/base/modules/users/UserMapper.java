/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.users;

import com.vi.model.dao.UserDAO;
import com.vi.model.dto.UserDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO userDAOToUserDTO(UserDAO userDAO);

	UserDAO userDTOToUserDAO(UserDTO userDTO);

	List<UserDTO> userDAOListToUserDTOList(List<UserDAO> userDAOList);

	List<UserDAO> userDTOListToUserDAOList(List<UserDTO> userDTOList);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void assignValues(UserDTO dto, @MappingTarget UserDAO entity);
}
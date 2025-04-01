/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.users;

import com.vi.base.modules.users.UserMapper;
import com.vi.model.dao.UserDAO;
import com.vi.model.dto.UserDTO;
import com.vi.model.dto.UserDTOCustom;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class UserJPAAdapterCustom implements UserPersistentCustom {
	@Autowired
	UserRepositoryCustom userRepositoryCustom;

	@Override
	public List<UserDTOCustom> fetchAll() {
		var userDAOList = userRepositoryCustom.findAll();
		return UserMapperCustom.INSTANCE.userDAOListToUserDTOListCustom(userDAOList);
	}

	@Override
	public UserDTOCustom get(Long id) {
		var userDAO = userRepositoryCustom.findById(id);
		if (userDAO.isPresent()) {
			return UserMapperCustom.INSTANCE.userDAOToUserDTOCustom(userDAO.get());
		}
		return null;
	}

	@Override
	public UserDTOCustom create(UserDTOCustom userDTOCustom) {
		var userDAO = UserMapperCustom.INSTANCE.userDTOCustomToUserDAO(userDTOCustom);
		UserDAO newUser = userRepositoryCustom.save(userDAO);
		return UserMapperCustom.INSTANCE.userDAOToUserDTOCustom(newUser);
	}

	//updated
	@Override
	public UserDTOCustom update(UserDTOCustom userDTOCustom) {
		var userDAO = userRepositoryCustom.findById(userDTOCustom.getUserSeqNo())
				 .orElseThrow(() -> new EntityNotFoundException("Record not found with id: " + userDTOCustom.getUserSeqNo()));
		var oldData = UserMapperCustom.INSTANCE.userDAOToUserDTOCustom(userDAO);
		UserMapperCustom.INSTANCE.assignValuesCustom(userDTOCustom, userDAO);
		UserDAO newUser = userRepositoryCustom.save(userDAO);
		return UserMapperCustom.INSTANCE.userDAOToUserDTOCustom(newUser);
	}
	

	@Override
	public Boolean delete(Long id) {
		var userDAO = userRepositoryCustom.getById(id);
	//	userDAO.setDeleted(true);
		userRepositoryCustom.save(userDAO);
		return true;
	}
}

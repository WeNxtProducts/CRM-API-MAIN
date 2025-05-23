/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.users;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dao.UserDAO;
import com.vi.model.dto.UserDTO;

public class UserServiceImpl implements UserService {

	private UserPersistent userPersistent;

	public UserServiceImpl(UserPersistent userPersistent) {
		this.userPersistent = userPersistent;
	}

	@Override
	public List<UserDTO> fetchAll() {
		return userPersistent.fetchAll();
	}

	@Override
	public UserDTO get(Long id) {
		return userPersistent.get(id);
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		return userPersistent.create(userDTO);
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		return userPersistent.update(userDTO);
	}

	@Override
	public Boolean delete(Long id) {
		return userPersistent.delete(id);
	}

	@Override
	public List<UserDTO> filterData(String search) {
		return userPersistent.filterData(search);
	}

	@Override
	public List<UserDTO> filterData(JsonNode search,int page,int size) {
		return userPersistent.filterData(search, page, size);
	}
	
	@Override
	public List<UserDTO> filterData(JsonNode search) {
		return userPersistent.filterData(search);
	}
	

}
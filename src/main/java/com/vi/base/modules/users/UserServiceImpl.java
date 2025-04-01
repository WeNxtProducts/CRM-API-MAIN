/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.users;

import com.fasterxml.jackson.databind.JsonNode;
import com.vi.model.dto.UserDTO;
import java.util.List;

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
}
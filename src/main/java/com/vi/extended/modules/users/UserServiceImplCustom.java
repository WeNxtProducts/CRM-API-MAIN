/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.UserDTOCustom;

public class UserServiceImplCustom implements UserServiceCustom {

	private UserPersistentCustom userpersistentCustom;

	public UserServiceImplCustom(UserPersistentCustom userpersistentCustom) {
		this.userpersistentCustom = userpersistentCustom;
	}

	@Override
	public List<UserDTOCustom> fetchAll() {
		return userpersistentCustom.fetchAll();
	}

	@Override
	public UserDTOCustom get(Long id) {
		return userpersistentCustom.get(id);
	}

	@Override
	public UserDTOCustom create(UserDTOCustom userDTOCustom) {
		return userpersistentCustom.create(userDTOCustom);
	}

	@Override
	public UserDTOCustom update(UserDTOCustom userDTOCustom) {
		return userpersistentCustom.update(userDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return userpersistentCustom.delete(id);
	}
}
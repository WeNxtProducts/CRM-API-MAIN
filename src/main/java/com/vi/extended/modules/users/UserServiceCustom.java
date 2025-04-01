/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.users;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.UserDAO;
import com.vi.model.dto.UserDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface UserServiceCustom extends BaseService<UserDTOCustom> {

}

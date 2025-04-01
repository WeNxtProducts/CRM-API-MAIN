/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.users;

import com.vi.model.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDAO,Long>, JpaSpecificationExecutor<UserDAO> {

}

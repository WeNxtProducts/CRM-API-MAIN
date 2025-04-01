/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.UserDAO;

@Repository
public interface UserRepositoryCustom extends JpaRepository<UserDAO,Long> {
	
//	@Query("SELECT COUNT(*) AS records FROM UserDAO")
//	long countByUserSeqNo();

}

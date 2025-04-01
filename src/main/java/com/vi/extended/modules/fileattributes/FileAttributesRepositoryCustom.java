/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.fileattributes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vi.model.dao.FileAttributesDAO;

@Repository
public interface FileAttributesRepositoryCustom extends JpaRepository<FileAttributesDAO,Long> {
	
//	@Query("SELECT COUNT(*) AS records FROM FileAttributesDAO")
//	long countByFileAttributesSeqNo();

}

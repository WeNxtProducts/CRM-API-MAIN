/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.activitys;

import com.vi.model.dao.ActivityDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepositoryCustom extends JpaRepository<ActivityDAO,Long> {

}

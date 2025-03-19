/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_parts;

import com.vi.model.dao.Pxt_fac_partDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pxt_fac_partRepositoryCustom extends JpaRepository<Pxt_fac_partDAO,Long> {

}

/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_place_dfns;

import com.vi.model.dao.Pxt_fac_place_dfnDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface Pxt_fac_place_dfnRepository extends JpaRepository<Pxt_fac_place_dfnDAO,Long>, JpaSpecificationExecutor<Pxt_fac_place_dfnDAO> {

}

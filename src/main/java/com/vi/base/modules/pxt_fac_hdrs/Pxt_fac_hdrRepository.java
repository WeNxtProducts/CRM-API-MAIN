/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_hdrs;

import com.vi.model.dao.Pxt_fac_hdrDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface Pxt_fac_hdrRepository extends JpaRepository<Pxt_fac_hdrDAO,Long>, JpaSpecificationExecutor<Pxt_fac_hdrDAO> {

}

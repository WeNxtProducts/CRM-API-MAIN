/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_rsk_cvrs;

import com.vi.model.dao.Pxt_fac_rsk_cvrDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface Pxt_fac_rsk_cvrRepository extends JpaRepository<Pxt_fac_rsk_cvrDAO,Long>, JpaSpecificationExecutor<Pxt_fac_rsk_cvrDAO> {

}

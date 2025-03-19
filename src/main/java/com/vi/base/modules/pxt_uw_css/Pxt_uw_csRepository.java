/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_uw_css;

import com.vi.model.dao.Pxt_uw_csDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface Pxt_uw_csRepository extends JpaRepository<Pxt_uw_csDAO,Long>, JpaSpecificationExecutor<Pxt_uw_csDAO> {

}

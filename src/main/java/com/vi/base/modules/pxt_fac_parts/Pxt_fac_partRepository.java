/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.pxt_fac_parts;

import com.vi.model.dao.Pxt_fac_partDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface Pxt_fac_partRepository extends JpaRepository<Pxt_fac_partDAO,Long>, JpaSpecificationExecutor<Pxt_fac_partDAO> {

}

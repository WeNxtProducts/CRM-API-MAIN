/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.pxt_fac_place_dfns;

import com.vi.model.dao.Pxt_fac_place_dfnDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pxt_fac_place_dfnRepositoryCustom extends JpaRepository<Pxt_fac_place_dfnDAO,Long> {

}

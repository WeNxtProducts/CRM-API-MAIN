/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailparams;

import com.vi.model.dao.LjmEmailParamDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LjmEmailParamRepository extends JpaRepository<LjmEmailParamDAO,Long>, JpaSpecificationExecutor<LjmEmailParamDAO> {

}

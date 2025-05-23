/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.ljmemailtemplates;

import com.vi.model.dao.LjmEmailTemplateDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LjmEmailTemplateRepository extends JpaRepository<LjmEmailTemplateDAO,Long>, JpaSpecificationExecutor<LjmEmailTemplateDAO> {


}

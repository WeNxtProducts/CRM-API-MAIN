/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.ljmemailtemplates;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.LjmEmailTemplateDAO;
import com.vi.model.dto.LjmEmailTemplateDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface LjmEmailTemplateServiceCustom extends BaseService<LjmEmailTemplateDTOCustom> {

}

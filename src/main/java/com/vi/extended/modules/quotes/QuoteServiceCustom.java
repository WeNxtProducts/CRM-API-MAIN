/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.quotes;

import org.springframework.beans.factory.annotation.Autowired;

import com.vi.corelib.base.BaseService;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.QuoteDTOCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface QuoteServiceCustom extends BaseService<QuoteDTOCustom> {

}

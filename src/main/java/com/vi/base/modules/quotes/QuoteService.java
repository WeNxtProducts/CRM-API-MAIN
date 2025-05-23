/*
 - Version Number 0.0.1
*/

package com.vi.base.modules.quotes;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.vi.corelib.filter.FilterService;
import com.vi.model.dao.QuoteDAO;
import com.vi.model.dto.QuoteDTO;

public interface QuoteService extends FilterService<QuoteDTO> {

//	QuoteDTO mapToDTO(QuoteDAO savedQuote);

}

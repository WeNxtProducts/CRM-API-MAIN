/*
 - Version Number 0.0.1
*/

package com.vi.extended.modules.ljmemailtemplates;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vi.model.dto.LjmEmailTemplateDTOCustom;

public class LjmEmailTemplateServiceImplCustom implements LjmEmailTemplateServiceCustom {

	private LjmEmailTemplatePersistentCustom ljmEmailTemplatepersistentCustom;

	public LjmEmailTemplateServiceImplCustom(LjmEmailTemplatePersistentCustom ljmEmailTemplatepersistentCustom) {
		this.ljmEmailTemplatepersistentCustom = ljmEmailTemplatepersistentCustom;
	}

	@Override
	public List<LjmEmailTemplateDTOCustom> fetchAll() {
		return ljmEmailTemplatepersistentCustom.fetchAll();
	}

	@Override
	public LjmEmailTemplateDTOCustom get(Long id) {
		return ljmEmailTemplatepersistentCustom.get(id);
	}

	@Override
	public LjmEmailTemplateDTOCustom create(LjmEmailTemplateDTOCustom ljmEmailTemplateDTOCustom) {
		return ljmEmailTemplatepersistentCustom.create(ljmEmailTemplateDTOCustom);
	}

	@Override
	public LjmEmailTemplateDTOCustom update(LjmEmailTemplateDTOCustom ljmEmailTemplateDTOCustom) {
		return ljmEmailTemplatepersistentCustom.update(ljmEmailTemplateDTOCustom);
	}

	@Override
	public Boolean delete(Long id) {
		return ljmEmailTemplatepersistentCustom.delete(id);
	}
}
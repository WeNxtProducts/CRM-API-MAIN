package com.vi.base.emailTemplate;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormFieldsDTO {
	
	@JsonProperty("formFields")
	private Map<String, String> formFields;
	
	@JsonProperty("label")
	private String label;

	// Getters and setters
	public Map<String, String> getFormFields() {
		return formFields;
	}

	public void setFormFields(Map<String, String> formFields) {
		this.formFields = formFields;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}

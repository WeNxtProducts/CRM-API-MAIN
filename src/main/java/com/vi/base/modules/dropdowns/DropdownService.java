package com.vi.base.modules.dropdowns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vi.model.dto.DropdownRequestDTO;

@Service
public class DropdownService {

    @Value("${external.api.countryDropdownUrl}")
    private String countryDropdownUrl;

    @Value("${external.api.titleDropdownUrl}")
    private String titleDropdownUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Object getCountryList(DropdownRequestDTO requestDTO, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", token);
            HttpEntity<DropdownRequestDTO> entity = new HttpEntity<>(requestDTO, headers);

            ResponseEntity<Object> response = restTemplate.postForEntity(
                    countryDropdownUrl,
                    entity,
                    Object.class
            );

            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch country list: " + e.getMessage());
        }
    }
    
    public Object getTitleList(DropdownRequestDTO requestDTO, String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", token);
            HttpEntity<DropdownRequestDTO> entity = new HttpEntity<>(requestDTO, headers);

            ResponseEntity<Object> response = restTemplate.postForEntity(
            		titleDropdownUrl,
                    entity,
                    Object.class
            );

            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch country list: " + e.getMessage());
        }
    }
}

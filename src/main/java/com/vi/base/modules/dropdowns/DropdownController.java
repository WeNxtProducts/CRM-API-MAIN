package com.vi.base.modules.dropdowns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vi.model.dto.DropdownRequestDTO;


@RestController
@RequestMapping("/api/dropdown")
public class DropdownController {

    @Autowired
    private DropdownService dropdownService;

    @PostMapping("/eway/country")
    public ResponseEntity<?> getCountries(
            @RequestBody DropdownRequestDTO dto,
            @RequestHeader("X-AUTH-TOKEN") String tokenFromUI) {
        
        // Convert to proper Authorization format
        String authorizationHeader = "Bearer " + tokenFromUI;

        Object countryList = dropdownService.getCountryList(dto, authorizationHeader);
        return ResponseEntity.ok(countryList);
    }
    
    @PostMapping("/eway/title")
    public ResponseEntity<?> getTitles(
            @RequestBody DropdownRequestDTO dto,
            @RequestHeader("X-AUTH-TOKEN") String tokenFromUI) {
        
        // Convert to proper Authorization format
        String authorizationHeader = "Bearer " + tokenFromUI;

        Object countryList = dropdownService.getTitleList(dto, authorizationHeader);
        return ResponseEntity.ok(countryList);
    }

    
}

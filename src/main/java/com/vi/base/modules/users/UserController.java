/*
1 - Version Number s0.0.1
*/

package com.vi.base.modules.users;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vi.model.dao.UserDAO;
import com.vi.model.dto.BrokerCreationReq;
import com.vi.model.dto.ChangePasswordReq;
import com.vi.model.dto.IssuerCraeationReq;
import com.vi.model.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/User")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
	UserRepository userRepo;
   
   @Value("${auth.token.validation.url}")
   private String tokenValidationBaseUrl;
   
   
   
   
   

    // GET ALL USERS
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll(
            @Nullable @RequestParam HashMap<String, String> json,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1000") int size) {
        json.remove("page");
        json.remove("size");
        json.put("deleted", "false");
        JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

        System.out.println("Request JSON: " + jsonRequest);
        System.out.println("Page: " + page + ", Size: " + size);
        return ResponseEntity.ok().body(userService.filterData(jsonRequest, page, size));
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable Long id) {
        var userDTO = userService.get(id);
        return ResponseEntity.ok().body(userDTO);
    }
   
	// Login
    @GetMapping("/Login")
    public ResponseEntity<Map<String, Object>> getUserDetails(@RequestHeader("X-AUTH-TOKEN") String token) {

        String validateUrl = "http://192.168.1.179:8086/authentication/validateToken?token=" + token;
//      String validateUrl = tokenValidationBaseUrl + "?token=" + token;

        Map<String, Object> responseMap = new HashMap<>();

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(validateUrl, null, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();

                Boolean isValid = (Boolean) responseBody.get("validateToken");
                if (Boolean.TRUE.equals(isValid)) {
                    String loginId = (String) responseBody.get("loginId");
//                    System.out.println("------------loginId from token: " + loginId);


                    if (loginId != null && !loginId.isEmpty()) {
                        Optional<UserDAO> optionalUser = userRepo.findByLoginId(loginId);

                        if (optionalUser.isPresent()) {
                            UserDAO user = optionalUser.get(); 
                            responseMap.put("success", true);
                            responseMap.put("message", "User retrieved successfully");
                            responseMap.put("data", user);
                            return ResponseEntity.ok(responseMap);
                        } else {
                            responseMap.put("success", false);
                            responseMap.put("message", "User not found for loginId: " + loginId);
                            responseMap.put("data", null);
                            return ResponseEntity.status(404).body(responseMap);
                        }

                    } else {
                        responseMap.put("success", false);
                        responseMap.put("message", "Login ID missing in token validation response");
                        responseMap.put("data", null);
                        return ResponseEntity.badRequest().body(responseMap);
                    }
                }
            }

            responseMap.put("success", false);
            responseMap.put("message", "Invalid or expired token");
            responseMap.put("data", null);
            return ResponseEntity.status(401).body(responseMap);

        } catch (Exception e) {
            responseMap.put("success", false);
            responseMap.put("message", "Token validation failed: " + e.getMessage());
            responseMap.put("data", null);
            return ResponseEntity.status(500).body(responseMap);
        }
    }



    // MAPPER CLASS FOR DTO CONVERSION
    public static class UserDTOMapper {

        // Map BrokerCreationReq to UserDTO (Sales)
        public static UserDTO toUserDTO(BrokerCreationReq brokerCreationReq, UserRepository userRepo) {
            var loginInfo = brokerCreationReq.getLoginInformation();
            var personalInfo = brokerCreationReq.getPersonalInformation();
            
            String assignedToLoginId = personalInfo.getApprovalId();
            String assignedToName = null;
            String assignedToEmail = null;
            String assignedTo = null;

            if (assignedToLoginId != null && !assignedToLoginId.isEmpty()) {
                Optional<UserDAO> assignedUserOpt = userRepo.findByLoginId(assignedToLoginId);
                if (assignedUserOpt.isPresent()) {
                    UserDAO user = assignedUserOpt.get();
                    assignedToName = user.getUserName();
                    assignedToEmail = user.getUserEmail();
                    assignedTo = String.valueOf(user.getUserSeqNo()); 
                }
            }

            return UserDTO.builder()
                    .userName(personalInfo.getUserName())
                    .userEmail(personalInfo.getUserMail())
                    .userPhoneNo(personalInfo.getUserMobile())
                    .userPassword(loginInfo.getPassword())
                    .loginId(loginInfo.getLoginId())
                    .userRole("Sales")
                    .assignedTo(assignedTo)
                    .assignedToName(assignedToName)
                    .assignedToEmail(assignedToEmail)
                    .assignedToLoginId(assignedToLoginId)
                    .companyId(loginInfo.getCompanyId())
                    .isActive("Y".equalsIgnoreCase(loginInfo.getStatus()) ? "Y" : "N")
                    .createdBy(loginInfo.getCreatedBy())
                    .updatedBy(loginInfo.getCreatedBy())
                    .createdDate(new Date())
                    .deleted(false)
                    .build();
        }

        // Map IssuerCraeationReq to UserDTO (Underwriter or Manager)
        public static UserDTO toUserDTO(IssuerCraeationReq issuerCreationReq) {
            var loginInfo = issuerCreationReq.getLoginInformation();
            var personalInfo = issuerCreationReq.getPersonalInformation();

            String userRole;
            if ("issuer".equalsIgnoreCase(loginInfo.getUserType())) {
                if ("both".equalsIgnoreCase(loginInfo.getSubUserType())) {
                    userRole = "Underwriter";
                } else if ("high".equalsIgnoreCase(loginInfo.getSubUserType())) {
                    userRole = "Manager";
                } else {
                    userRole = "Issuer";
                }
            } else {
                userRole = loginInfo.getUserType(); 
            }

            return UserDTO.builder()
                    .userName(personalInfo.getUserName())
                    .userEmail(personalInfo.getUserMail())
                    .userPhoneNo(personalInfo.getUserMobile())
                    .userPassword(loginInfo.getPassword())
                    .loginId(loginInfo.getLoginId())
                    .userRole(userRole)
                    .companyId(loginInfo.getCompanyId())
                    .isActive("Y".equalsIgnoreCase(loginInfo.getStatus()) ? "Y" : "N")
                    .createdBy(loginInfo.getCreatedBy())
                    .updatedBy(loginInfo.getCreatedBy())
                    .createdDate(new Date())
                    .deleted(false)
                    .build();
        }
    }

    // CREATE BROKER (SALES)
    @PostMapping("/createBroker")
    public ResponseEntity<UserDTO> createBroker(@RequestBody BrokerCreationReq brokerCreationReq) {
        UserDTO userDTO = UserDTOMapper.toUserDTO(brokerCreationReq, userRepo);
        var savedUser = userService.create(userDTO);
        return ResponseEntity.ok().body(savedUser);
    }

    // CREATE ISSUER (UNDERWRITER OR MANAGER)
    @PostMapping("/createIssuer")
    public ResponseEntity<UserDTO> createIssuer(@RequestBody IssuerCraeationReq issuerCreationReq) {
        UserDTO userDTO = UserDTOMapper.toUserDTO(issuerCreationReq);
        var savedUser = userService.create(userDTO);s
        return ResponseEntity.ok().body(savedUser);
    }

    // GENERIC CREATE (for direct UserDTO)
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        var savedUser = userService.create(userDTO);
        return ResponseEntity.ok().body(savedUser);
    }
    
    @PutMapping("/updatePassword")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody ChangePasswordReq request) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (!"ChangePassword".equalsIgnoreCase(request.getType())) {
                response.put("success", false);
                response.put("message", "Invalid request type");
                return ResponseEntity.badRequest().body(response);
            }

            Optional<UserDAO> optionalUser = userRepo.findByLoginId(request.getLoginId());
            if (optionalUser.isEmpty()) {
                response.put("success", false);
                response.put("message", "User not found for LoginId: " + request.getLoginId());
                return ResponseEntity.status(404).body(response);
            }

            UserDAO user = optionalUser.get();
            user.setUserPassword(request.getNewPassword()); // Add password encoding if needed

            userRepo.save(user);

            response.put("success", true);
            response.put("message", "Password updated successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error updating password: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    
    // UPDATE BROKER (SALES)
    @PutMapping("/updateBroker")
    public ResponseEntity<UserDTO> updateBroker(@RequestBody BrokerCreationReq brokerCreationReq) {
        var loginInfo = brokerCreationReq.getLoginInformation();
        var personalInfo = brokerCreationReq.getPersonalInformation();

        // Map to UserDTO
        UserDTO userDTO = UserDTO.builder()
                .userName(personalInfo.getUserName())
                .userEmail(personalInfo.getUserMail())
                .userPhoneNo(personalInfo.getUserMobile())
                .userPassword(loginInfo.getPassword())
                .loginId(loginInfo.getLoginId())
                .userRole("Sales")
                .companyId(loginInfo.getCompanyId())
                .isActive("Y".equalsIgnoreCase(loginInfo.getStatus()) ? "Y" : "N")
                .createdBy(loginInfo.getCreatedBy())
                .updatedBy(loginInfo.getCreatedBy())
                .build();

        return ResponseEntity.ok().body(updateUserFromDTO(userDTO));
    }

    // UPDATE ISSUER (UNDERWRITER OR MANAGER)
    @PutMapping("/updateIssuer")
    public ResponseEntity<UserDTO> updateIssuer(@RequestBody IssuerCraeationReq issuerCreationReq) {
        var loginInfo = issuerCreationReq.getLoginInformation();
        var personalInfo = issuerCreationReq.getPersonalInformation();

        String userRole;
        if ("issuer".equalsIgnoreCase(loginInfo.getUserType())) {
            if ("both".equalsIgnoreCase(loginInfo.getSubUserType())) {
                userRole = "Underwriter";
            } else if ("high".equalsIgnoreCase(loginInfo.getSubUserType())) {
                userRole = "Manager";
            } else {
                userRole = "Issuer";
            }
        } else {
            userRole = loginInfo.getUserType();
        }

        // Map to UserDTO
        UserDTO userDTO = UserDTO.builder()
                .userName(personalInfo.getUserName())
                .userEmail(personalInfo.getUserMail())
                .userPhoneNo(personalInfo.getUserMobile())
                .userPassword(loginInfo.getPassword())
                .loginId(loginInfo.getLoginId())
                .userRole(userRole)
                .companyId(loginInfo.getCompanyId())
                .isActive("Y".equalsIgnoreCase(loginInfo.getStatus()) ? "Y" : "N")
                .createdBy(loginInfo.getCreatedBy())
                .updatedBy(loginInfo.getCreatedBy())
                .build();

        return ResponseEntity.ok().body(updateUserFromDTO(userDTO));
    }

    // 🔁 Reusable method inside controller to perform actual update
    private UserDTO updateUserFromDTO(UserDTO userDTO) {
        Optional<UserDAO> optionalUser = userRepo.findByLoginId(userDTO.getLoginId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with login ID: " + userDTO.getLoginId());
        }

        UserDAO existingUser = optionalUser.get();

        // Update non-null fields
        if (userDTO.getUserName() != null) existingUser.setUserName(userDTO.getUserName());
        if (userDTO.getUserEmail() != null) existingUser.setUserEmail(userDTO.getUserEmail());
        if (userDTO.getUserPhoneNo() != null) existingUser.setUserPhoneNo(userDTO.getUserPhoneNo());
        if (userDTO.getUserPassword() != null && !userDTO.getUserPassword().isEmpty()) {
            existingUser.setUserPassword(userDTO.getUserPassword()); // Avoid empty password
        }
        if (userDTO.getUserRole() != null) existingUser.setUserRole(userDTO.getUserRole());
        if (userDTO.getCompanyId() != null) existingUser.setCompanyId(userDTO.getCompanyId());
        if (userDTO.getIsActive() != null) existingUser.setIsActive(userDTO.getIsActive());
        if (userDTO.getCreatedBy() != null) existingUser.setCreatedBy(userDTO.getCreatedBy());
        if (userDTO.getUpdatedBy() != null) existingUser.setUpdatedBy(userDTO.getUpdatedBy());

//        existingUser.setUpdatedDate(new Date()); // Set current timestamp

        // Save and convert back to DTO
        UserDAO savedUser = userRepo.save(existingUser);
        return UserDTO.builder()
                .loginId(savedUser.getLoginId())
                .userName(savedUser.getUserName())
                .userEmail(savedUser.getUserEmail())
                .userPhoneNo(savedUser.getUserPhoneNo())
                .userPassword(savedUser.getUserPassword())
                .userRole(savedUser.getUserRole())
                .companyId(savedUser.getCompanyId())
                .isActive(savedUser.getIsActive())
                .createdBy(savedUser.getCreatedBy())
                .updatedBy(savedUser.getUpdatedBy())
                .createdDate(savedUser.getCreatedDate())
                .updatedDate(savedUser.getUpdatedDate())
                .build();
    }


    // UPDATE USER
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        var updatedUser = userService.update(userDTO);
        return ResponseEntity.ok().body(updatedUser);
    }

    // FILTER DATA
    @GetMapping("/filter")
    public ResponseEntity<List<UserDTO>> filterData(@RequestParam(value = "search") String search) {
        return ResponseEntity.ok().body(userService.filterData(search));
    }

    @GetMapping("/filter2")
    public ResponseEntity<List<UserDTO>> filterData2(
            @RequestParam HashMap<String, Object> json,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1000") int size) {
        json.remove("page");
        json.remove("size");
        JsonNode jsonRequest = new ObjectMapper().convertValue(json, JsonNode.class);

        System.out.println("Request JSON: " + jsonRequest);
        System.out.println("Page: " + page + ", Size: " + size);
        return ResponseEntity.ok().body(userService.filterData(jsonRequest, page, size));
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteOne(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }
}
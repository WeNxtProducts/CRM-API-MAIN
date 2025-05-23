package com.vi.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Data
@Entity(name="WN_USER")
@Table(name = "WN_USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDAO {
    
    @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SEQ_NO")
    private Long userSeqNo;

    @Column(name = "USER_REF_ID")
    private String userRefId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_PHONE_NO")
    private String userPhoneNo;
    
    @Column(name = "USER_PASSWORD")
    private String userPassword;

    @Column(name = "USER_ROLE")
    private String userRole; 
    
    @Column(name = "LOGIN_ID")
    private String loginId;
    
//    @Column(name = "MOBILE_CODE")
//    private String mobileCode;
//    
//    @Column(name = "WHATSAPP_CODE")
//    private String whatsappCode;
//    
//    @Column(name = "WHATSAPP_NO")
//    private String whatsappNo;
//    
//    @Column(name = "COUNTRY_CODE")
//    private String countryCode;
//    
//    @Column(name = "COUNTRY_NAME")
//    private String countryName;
//    
//    @Column(name = "STATE_CODE")
//    private String stateCode;
//    
//    @Column(name = "STATE_NAME")
//    private String stateName;
//    
//    @Column(name = "CITY_CODE")
//    private String cityCode;
//    
//    @Column(name = "CITY_NAME")
//    private String cityName;
//    
//    @Column(name = "ADDRESS_1")
//    private String address1;
//    
//    @Column(name = "ADDRESS_2")
//    private String address2;
//    
//    @Column(name = "ADDRESS_3")
//    private String address3;
//    
//    @Column(name = "COMPANY_NAME")
//    private String companyName;
//    
//    @Column(name = "AGENCY_CODE")
//    private String agencyCode;
//    
////    @Column(name = "ATTACHED_BRANCHES")
////    private List<String>  attachedBranches;
////    
////    @Column(name = "ATTACHED_COMPANIES")
////    private List<String>  attachedCompanies;
////    
////    @Column(name = "ATTACHED_REGIONS")
////    private List<String>  attachedRegions;
    
    
    @Column(name = "COMPANY_ID")
    private String companyId;
    
    @Column(name = "IS_ACTIVE")
    private String isActive;
    
    @Column(name = "ASSIGNED_TO")
    private String assignedTo;
    
    @Column(name = "ASSIGNED_TO_LOGIN_ID")
    private String assignedToLoginId;
    
    @Column(name = "ASSIGNED_TO_NAME")
    private String assignedToName;
    
    @Column(name = "ASSIGNED_TO_EMAIL")
    private String assignedToEmail;
    
    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    
    @Column(name = "DELETED")
    private Boolean deleted=false;
    
    @Column(name = "DELETED_AT")
    private Date deletedAt;
    
    @Column(name = "DELETED_BY")
    private String deletedBy;
    
    @Column(name = "CURRENT_UNDWRITER")
    private String currUnderwriter;
    
    @Column(name = "CURRENT_UNDWRITER_NAME")
    private String currUnderwriterName;
    
    @Column(name = "CURRENT_UNDWRITER_EMAIL")
    private String currUnderwriterEmail;
    
    @PostPersist
    public void generateUserRefId() {
        if (this.userRefId == null && this.userSeqNo != null && this.userRole != null) {
            String prefix;

            switch (userRole.toLowerCase()) {
                case "manager":
                    prefix = "M";
                    break;
                case "sales":
                    prefix = "S";
                    break;
                case "underwriter":
                    prefix = "U";
                    break;
                case "admin":
                    prefix = "A";
                    break;
                default:
                    prefix = "X"; // fallback for unknown roles
            }

            this.userRefId = String.format("%s%07d", prefix, this.userSeqNo);
        }
    }


}


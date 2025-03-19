package com.vi.corelib;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserInfo {
	private Long userId;
	private String userName;
	private Date expiryDate;
	private Long defaultBranchId;
	private Long homeBranchId;
	// private List<Long> securityGroupId;
//
// 	private List<Security> security;
	private Long securityGroupId;  // TODO In Token its long not list, need to fix
	private String serviceCode;
}

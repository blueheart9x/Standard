package com.elcom.model.object;

import java.io.Serializable;

public class UserRole implements Serializable {

	private static final long serialVersionUID = 65742261949219529L;
	
	private String roleCode;
	private String loginId;

	public UserRole(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public UserRole(String loginId, String roleCode) {
		this.loginId = loginId;
		this.roleCode = roleCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}

package com.elcom.model.dto;

import java.sql.Timestamp;

public class AuthorizationResponseDTODetails {

    private String accessToken;
    private String refreshToken;
    private String companyName;
    private Long companyId;
    private Long id;
    private String uuid;
    private String email;
    private String fullName;
    private String avatar;
    private Integer status;
    private String mobile;
    private String address;
    private String skype;
    private String facebook;
    private Integer userType;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private Long careerId;
    private Integer companyType;

    public AuthorizationResponseDTODetails(String accessToken, String refreshToken, Long id, String email, String fullName, String mobile,
            String skype, String facebook, String avatar, Integer userType, Integer status, Timestamp createdAt,
            Timestamp lastLogin, String uuid, String address, String companyName, Long companyId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.fullName = fullName;
        this.avatar = avatar;
        this.status = status;
        this.companyId = companyId;
        this.companyName = companyName;
        this.mobile = mobile;
        this.skype = skype;
        this.facebook = facebook;
        this.userType = userType;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.address = address;
    }

    public AuthorizationResponseDTODetails(String accessToken, String refreshToken, Long id, String email, String fullName, String mobile,
            String skype, String facebook, String avatar, Integer userType, Integer status, Timestamp createdAt,
            Timestamp lastLogin, String uuid, String address, String companyName, Long companyId, Long careerId, Integer companyType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.fullName = fullName;
        this.avatar = avatar;
        this.status = status;
        this.companyId = companyId;
        this.companyName = companyName;
        this.mobile = mobile;
        this.skype = skype;
        this.facebook = facebook;
        this.userType = userType;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.address = address;
        this.careerId = careerId;
        this.companyType = companyType;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getCareerId() {
        return careerId;
    }

    public void setCareerId(Long careerId) {
        this.careerId = careerId;
    }

}

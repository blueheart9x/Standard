package com.elcom.data.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the USER_COMPANY database table.
 *
 */
@Entity
@Table(name = "user_company")
public class UserCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "COMPANY_ID")
    private String companyId;

    @Column(name = "LINK_CV")
    private String linkCv;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    public UserCompany() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getLinkCv() {
		return linkCv;
	}

	public void setLinkCv(String linkCv) {
		this.linkCv = linkCv;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}

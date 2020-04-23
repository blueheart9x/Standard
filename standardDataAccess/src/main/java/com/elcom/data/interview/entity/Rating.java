/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r")
    , @NamedQuery(name = "Rating.findById", query = "SELECT r FROM Rating r WHERE r.id = :id")
    , @NamedQuery(name = "Rating.findByJobId", query = "SELECT r FROM Rating r WHERE r.jobId = :jobId")
    , @NamedQuery(name = "Rating.findByUserId", query = "SELECT r FROM Rating r WHERE r.userId = :userId")
    , @NamedQuery(name = "Rating.findByNote", query = "SELECT r FROM Rating r WHERE r.note = :note")
    , @NamedQuery(name = "Rating.findByRating", query = "SELECT r FROM Rating r WHERE r.rating = :rating")
    , @NamedQuery(name = "Rating.findByCreatedAt", query = "SELECT r FROM Rating r WHERE r.createdAt = :createdAt")})
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = true)
    @Column(name = "job_id")
    private Long jobId;
    
    @Basic(optional = true)
    @Column(name = "user_id")
    private Long userId;
    
    @Basic(optional = true)
    @Column(name = "note")
    private String note;
    
    @Basic(optional = true)
    @Column(name = "rating")
    private String rating;
    
    @Basic(optional = true)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    //Company
    @Transient
    private Long companyId;
    @Transient
    private String companyName;
    
    //Job
    @Transient
    private String jobName;
    
    //User
    @Transient
    private String userName;

    public Rating() {
    }

    public Rating(Long id) {
        this.id = id;
    }

    public Rating(Long id, Long jobId, Long userId, String note, String rating, Date createdAt) {
        this.id = id;
        this.jobId = jobId;
        this.userId = userId;
        this.note = note;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elcom.data.interview.entity.Rating[ id=" + id + " ]";
    }
}

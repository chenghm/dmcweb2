package com.cinsec.dmc.base.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DmcSite
 * 
 */
@Entity
public class DmcSite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String summary;
    @Column
    private String description;
    
    @Column(name="UPDATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
    
    @Column(name="UPDATED_USER_ID")
    private String updatedUserId;
    
    @Column(name="CREATED_TIME",updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    
    @Column(name="CREATED_USER_ID",updatable=false)
    private String createdUserId;
    

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public int getId() {
        return id;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cinsec.dmc.base.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Administrator
 */
@Entity
@Table(name = "MSTB_DEPARTMENT")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;
	@Basic(optional = false)
	@Column(name = "UNIT_CODE")
	private String unitCode;
	@Column(name = "CITY_CODE")
	private String cityCode;
	@Column(name = "DEPT_TYPE")
	private String deptType;
	@Column(name = "Z_DFUNC_DESC_ZHS")
	private String zDfuncDescZhs;
	@Column(name = "Z_DFUNC_DESC_ENG")
	private String zDfuncDescEng;
	@Column(name = "DEPT_NAME_CN")
	private String deptNameCn;
	@Column(name = "DEPT_NAME_EN")
	private String deptNameEn;
	@Column(name = "DEPT_FIN_CODE")
	private String deptFinCode;
	@Column(name = "IS_FUNCTIONAL")
	private String isFunctional;
	@Column(name = "DEPT_STATUS")
	private String deptStatus;
	@Column(name = "DEPT_UPDATE_DT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deptUpdateDt;
	@Column(name = "BUSINESS_UNIT")
	private String businessUnit;
	@Column(name = "EFFDT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date effdt;
	@Column(name = "MANAGER_ID")
	private String managerId;
	@Column(name = "OFFICER_CD")
	private String officerCd;
	@Column(name = "XLATLONGNAME")
	private String xlatlongname;
	@Column(name = "SAL_ADMIN_PLAN")
	private String salAdminPlan;
	@Column(name = "PARENT_NODE_NAME")
	private String parentNodeName;
	@Column(name = "TREE_LEVEL_NUM")
	private Integer treeLevelNum;
	@Column(name = "Z_LOCATION_ID")
	private String zLocationId;
	@Column(name = "Z_LOCATION_DESCR")
	private String zLocationDescr;
	@Column(name = "CITY")
	private String city;
	@Column(name = "COMPANY")
	private String company;
	@Column(name = "LOCATION")
	private String location;
	@Column(name = "Z_TAX_CENTER")
	private String zTaxCenter;
	@Column(name = "DESCR")
	private String descr;
	@Column(name = "Z_DEPT_ATTR_ID")
	private String zDeptAttrId;
	@Column(name = "Z_DEPT_FUNCTION")
	private String zDeptFunction;
	@Basic(optional = false)
	@Column(name = "RECORD_STATE")
	private Integer recordState;
	@Column(name = "CREATED_USER_ID")
	private String createdUserId;
	@Column(name = "CREATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	@Column(name = "UPDATED_USER_ID")
	private String updatedUserId;
	@Column(name = "UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	public Department() {
	}

	public Department(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Department(Long departmentId, String unitCode, Integer recordState) {
		this.departmentId = departmentId;
		this.unitCode = unitCode;
		this.recordState = recordState;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getZDfuncDescZhs() {
		return zDfuncDescZhs;
	}

	public void setZDfuncDescZhs(String zDfuncDescZhs) {
		this.zDfuncDescZhs = zDfuncDescZhs;
	}

	public String getZDfuncDescEng() {
		return zDfuncDescEng;
	}

	public void setZDfuncDescEng(String zDfuncDescEng) {
		this.zDfuncDescEng = zDfuncDescEng;
	}

	public String getDeptNameCn() {
		return deptNameCn;
	}

	public void setDeptNameCn(String deptNameCn) {
		this.deptNameCn = deptNameCn;
	}

	public String getDeptNameEn() {
		return deptNameEn;
	}

	public void setDeptNameEn(String deptNameEn) {
		this.deptNameEn = deptNameEn;
	}

	public String getDeptFinCode() {
		return deptFinCode;
	}

	public void setDeptFinCode(String deptFinCode) {
		this.deptFinCode = deptFinCode;
	}

	public String getIsFunctional() {
		return isFunctional;
	}

	public void setIsFunctional(String isFunctional) {
		this.isFunctional = isFunctional;
	}

	public String getDeptStatus() {
		return deptStatus;
	}

	public void setDeptStatus(String deptStatus) {
		this.deptStatus = deptStatus;
	}

	public Date getDeptUpdateDt() {
		return deptUpdateDt;
	}

	public void setDeptUpdateDt(Date deptUpdateDt) {
		this.deptUpdateDt = deptUpdateDt;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Date getEffdt() {
		return effdt;
	}

	public void setEffdt(Date effdt) {
		this.effdt = effdt;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getOfficerCd() {
		return officerCd;
	}

	public void setOfficerCd(String officerCd) {
		this.officerCd = officerCd;
	}

	public String getXlatlongname() {
		return xlatlongname;
	}

	public void setXlatlongname(String xlatlongname) {
		this.xlatlongname = xlatlongname;
	}

	public String getSalAdminPlan() {
		return salAdminPlan;
	}

	public void setSalAdminPlan(String salAdminPlan) {
		this.salAdminPlan = salAdminPlan;
	}

	public String getParentNodeName() {
		return parentNodeName;
	}

	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;
	}

	public Integer getTreeLevelNum() {
		return treeLevelNum;
	}

	public void setTreeLevelNum(Integer treeLevelNum) {
		this.treeLevelNum = treeLevelNum;
	}

	public String getZLocationId() {
		return zLocationId;
	}

	public void setZLocationId(String zLocationId) {
		this.zLocationId = zLocationId;
	}

	public String getZLocationDescr() {
		return zLocationDescr;
	}

	public void setZLocationDescr(String zLocationDescr) {
		this.zLocationDescr = zLocationDescr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getZTaxCenter() {
		return zTaxCenter;
	}

	public void setZTaxCenter(String zTaxCenter) {
		this.zTaxCenter = zTaxCenter;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getZDeptAttrId() {
		return zDeptAttrId;
	}

	public void setZDeptAttrId(String zDeptAttrId) {
		this.zDeptAttrId = zDeptAttrId;
	}

	public String getZDeptFunction() {
		return zDeptFunction;
	}

	public void setZDeptFunction(String zDeptFunction) {
		this.zDeptFunction = zDeptFunction;
	}

	public Integer getRecordState() {
		return recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (departmentId != null ? departmentId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// not set
		if (!(object instanceof Department)) {
			return false;
		}
		Department other = (Department) object;
		if ((this.departmentId == null && other.departmentId != null)
				|| (this.departmentId != null && !this.departmentId
						.equals(other.departmentId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final String msg = "com.amway.frm.afw.entity.Department[departmentId="
			+ departmentId + "]";
		return msg;
	}

}

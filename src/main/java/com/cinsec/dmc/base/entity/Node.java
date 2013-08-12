package com.cinsec.dmc.base.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the t_node database table.
 * 
 */
@Entity
@Table(name="t_node")
public class Node implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="actived_time")
	private Date activedTime;

	@Column(name="black_list")
	private int blackList;

	@Column(name="corp_name")
	private String corpName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;

	@Column(name="created_user")
	private String createdUser;

	private String descn;

	@Column(name="fetch_content")
	private int fetchContent;

	@Column(name="file_path")
	private String filePath;

	@Column(name="is_online")
	private byte isOnline;

	private byte status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;

	@Column(name="updated_user")
	private String updatedUser;

	public Node() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActivedTime() {
		return this.activedTime;
	}

	public void setActivedTime(Date activedTime) {
		this.activedTime = activedTime;
	}

	public int getBlackList() {
		return this.blackList;
	}

	public void setBlackList(int blackList) {
		this.blackList = blackList;
	}

	public String getCorpName() {
		return this.corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getDescn() {
		return this.descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public int getFetchContent() {
		return this.fetchContent;
	}

	public void setFetchContent(int fetchContent) {
		this.fetchContent = fetchContent;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public byte getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(byte isOnline) {
		this.isOnline = isOnline;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUser() {
		return this.updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

}
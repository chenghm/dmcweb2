package com.cinsec.dmc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_node")
public class Node {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(unique = true, nullable = false)
	    private Integer id;

	 	@Column(name = "updated_time")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date updatedTime;

	    @Column(name = "updated_user_id")
	    private Integer updatedUserId;

	    @Column(name = "created_time",updatable=false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdTime;

	    @Column(name = "created_user_id",updatable=false)
	    private Integer createdUserId; 
	    
	    @Column(unique =true,nullable=false)
	    private Integer number;//编号
	    
	    @Column(nullable=false,length=128)
	    private String companyName;//单位名称

//	    private 
}

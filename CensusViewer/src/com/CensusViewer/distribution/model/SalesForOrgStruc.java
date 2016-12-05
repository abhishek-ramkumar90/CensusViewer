package com.CensusViewer.distribution.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SALES_FORCE_ORG_STRUC",schema = "distribution")
public class SalesForOrgStruc {

	@Id
	@GenericGenerator(name="seq_id", strategy="com.mars.distribution.id.generator.SalesForOrgMasterIdGenerator")
	@GeneratedValue(generator="seq_id")
	@Column(name = "SFORGSTRUC_ID", unique = true, nullable = false, length = 20)
	private String sforgstrucId;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	public String getSforgstrucId() {
		return sforgstrucId;
	}
	public void setSforgstrucId(String sforgstrucId) {
		this.sforgstrucId = sforgstrucId;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}

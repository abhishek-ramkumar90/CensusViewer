package com.mars.distribution.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SALES_FORCE_ORG_MASTER_INTER",schema = "distribution")
public class SalesForOrgMasterInter {

	@Id
	@GenericGenerator(name="seq_id", strategy="com.mars.distribution.id.generator.SalesForOrgInterIdGenerator")
	@GeneratedValue(generator="seq_id")
	@Column(name = "SFORG_INTER_ID", unique = true, nullable = false, length = 20)
	private String sforgInterId;
	
	@Column(name="SFORG_INTER_NAME")
	private String sforgInterName;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="SFORG_INTER_ID")
	private Collection<SalesForOrgStruc> sforgstrucId;
	
	public Collection<SalesForOrgStruc> getSforgstrucId() {
		return sforgstrucId;
	}

	public void setSforgstrucId(Collection<SalesForOrgStruc> sforgstrucId) {
		this.sforgstrucId = sforgstrucId;
	}

	public String getSforgInterId() {
		return sforgInterId;
	}

	public void setSforgInterId(String sforgInterId) {
		this.sforgInterId = sforgInterId;
	}

	public String getSforgInterName() {
		return sforgInterName;
	}

	public void setSforgInterName(String sforgInterName) {
		this.sforgInterName = sforgInterName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}

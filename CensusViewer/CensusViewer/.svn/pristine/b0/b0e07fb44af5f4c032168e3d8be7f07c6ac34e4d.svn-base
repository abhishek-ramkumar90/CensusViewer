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
@Table(name="SALES_FORCE_ORG_MASTER",schema = "distribution")
public class SalesForOrgMaster {

	@Id
	@GenericGenerator(name="seq_id", strategy="com.mars.distribution.id.generator.SalesForOrgIdGenerator")
	@GeneratedValue(generator="seq_id")
	@Column(name = "SFORG_ID", unique = true, nullable = false, length = 20)
	private String sforgId;
	
	@Column(name="SFORG_TYPE")
	private String sforgType;
	
	@Column(name="CREATED_ON")
	private Date createdOn;

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="SFORG_ID")
	private Collection<SalesForOrgMasterInter> salesForOrgMasterInters;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="SFORG_ID")
	private Collection<SalesForOrgSpecialization> splIds;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="SFORG_ID")
	private Collection<SalesForOrgStruc> sforgstrucId;
	
	public Collection<SalesForOrgStruc> getSforgstrucId() {
		return sforgstrucId;
	}

	public void setSforgstrucId(Collection<SalesForOrgStruc> sforgstrucId) {
		this.sforgstrucId = sforgstrucId;
	}

	public Collection<SalesForOrgMasterInter> getSalesForOrgMasterInters() {
		return salesForOrgMasterInters;
	}

	public void setSalesForOrgMasterInters(
			Collection<SalesForOrgMasterInter> salesForOrgMasterInters) {
		this.salesForOrgMasterInters = salesForOrgMasterInters;
	}

	public Collection<SalesForOrgSpecialization> getSplIds() {
		return splIds;
	}

	public void setSplIds(Collection<SalesForOrgSpecialization> splIds) {
		this.splIds = splIds;
	}

	public String getSforgId() {
		return sforgId;
	}

	public void setSforgId(String sforgId) {
		this.sforgId = sforgId;
	}

	public String getSforgType() {
		return sforgType;
	}

	public void setSforgType(String sforgType) {
		this.sforgType = sforgType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	
}

package com.CensusViewer.distribution.model;

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
@Table(name="SALES_FORCE_ORG_SPEL",schema = "distribution")
public class SalesForOrgSpecialization {

	@Id
	@GenericGenerator(name="seq_id", strategy="com.mars.distribution.id.generator.SalesForOrgSpelIdGenerator")
	@GeneratedValue(generator="seq_id")
	@Column(name = "SFORGSPEL_ID", unique = true, nullable = false, length = 20)
	private String splId;
	
	@Column(name = "SFORGSPEL_NAME")
	private String splName;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="SFORGSPEL_ID")
	private Collection<SalesForOrgStruc> sforgstrucId;
	
	public Collection<SalesForOrgStruc> getSforgstrucId() {
		return sforgstrucId;
	}
	public void setSforgstrucId(Collection<SalesForOrgStruc> sforgstrucId) {
		this.sforgstrucId = sforgstrucId;
	}
	public String getSplId() {
		return splId;
	}
	public void setSplId(String splId) {
		this.splId = splId;
	}
	public String getSplName() {
		return splName;
	}
	public void setSplName(String splName) {
		this.splName = splName;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
}

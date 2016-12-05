package com.mars.distribution.model;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Intermediaries",schema="distribution")
public class Intermediary implements Serializable{
	@Id
	@Column(name="INTERM_ID")
	private String intermId;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true, fetch=FetchType.EAGER)
	@JoinTable(name="INTERMEDIARY_RELATION",schema="distribution")
	private List<Intermediary> intermediaryCollection=new LinkedList<Intermediary>();

	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="MODIFIED_ON")
	private Date modifiedOn;
	
	@Column(name="ORG_ID")
	private String orgId;

	@Column(name="ROOT_STATUS")
	private int rootStatus;
	
	@Column(name="INTM_TYPE")
	private String intermType;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="ZONE_ID")
	private String zoneId;
	
	@Column(name="REGION_ID")
	private String regionId;
	
	@Column(name="BRANCH_ID")
	private String branchId;
	
	@Column(name="TERRITORY_ID")
	private String territoryId;
	
	@Column(name="VILL_TOWN_TYPE")
	private String villTownType;
	
	@Column(name="VILL_TOWN_ID")
	private int villTownId;
	
	@Column(name="ADDRESS")
	private String address;

	@Column(name="STATUS")
	private int status;
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIntermId() {
		return intermId;
	}

	public void setIntermId(String intermId) {
		this.intermId = intermId;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	
	public int getRootStatus() {
		return rootStatus;
	}

	public void setRootStatus(int rootStatus) {
		this.rootStatus = rootStatus;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public String getIntermType() {
		return intermType;
	}

	public void setIntermType(String intermType) {
		this.intermType = intermType;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	
	public List<Intermediary> getIntermediaryCollection() {
		return intermediaryCollection;
	}

	public void setIntermediaryCollection(List<Intermediary> intermediaryCollection) {
		this.intermediaryCollection = intermediaryCollection;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(String territoryId) {
		this.territoryId = territoryId;
	}

	public String getVillTownType() {
		return villTownType;
	}

	public void setVillTownType(String villTownType) {
		this.villTownType = villTownType;
	}

	
	public int getVillTownId() {
		return villTownId;
	}

	public void setVillTownId(int villTownId) {
		this.villTownId = villTownId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
		
}

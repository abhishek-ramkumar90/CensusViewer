package com.mars.distribution.model;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@XmlRootElement(name="IntermediaryDetails")
@Entity
@Table(name="Intermediary_freeFlow",schema="distribution")
public class IntermediaryFreeFlow  implements Serializable{
	@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE )
	@Column(name="INTERM_ID")
	private int intermId;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="INTERMEDIARY_FREEFLOW_RELATION",schema="distribution")
	private List<IntermediaryFreeFlow> intermediaryCollection=new ArrayList<IntermediaryFreeFlow>();
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="MODIFIED_ON")
	private Date modifiedOn;
	
	@Column(name="ORG_ID")
	private String orgId;
	
	@Column(name="TYPE")
	private String type;
	
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
	private String villTownId;
	
	@Column(name="ADDRESS")
	private String address;

	@Column(name="STATUS")
	private int status;
	


	public int getIntermId() {
		return intermId;
	}
	public void setIntermId(int intermId) {
		this.intermId = intermId;
	}

	public List<IntermediaryFreeFlow> getIntermediaryCollection() {
		return intermediaryCollection;
	}
	public void setIntermediaryCollection(
			List<IntermediaryFreeFlow> intermediaryCollection) {
		this.intermediaryCollection = intermediaryCollection;
	}
	@XmlTransient
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	@XmlTransient
	public Date getModifiedOn() {
		return modifiedOn;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getVillTownId() {
		return villTownId;
	}

	public void setVillTownId(String villTownId) {
		this.villTownId = villTownId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}

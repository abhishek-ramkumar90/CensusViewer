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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name="USER_DETAILS" ,schema = "distribution")
public class User {
	@Id
	@GenericGenerator(name="seq_id", strategy="com.mars.distribution.id.generator.UserIdGenerator")
	@GeneratedValue(generator="seq_id")
	@Column(name = "USER_ID")
	private String userId;
	
	/*@OneToMany(cascade= CascadeType.ALL, mappedBy="createdBy")
	private Collection<DistrictChannel> distChannel;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="createdBy")
	private Collection<DistrictChannelIntermediaryDetail> distChannelInterDetail;
	
	public Collection<DistrictChannelIntermediaryDetail> getDistChannelInterDetail() {
		return distChannelInterDetail;
	}
	public void setDistChannelInterDetail(
			Collection<DistrictChannelIntermediaryDetail> distChannelInterDetail) {
		this.distChannelInterDetail = distChannelInterDetail;
	}
	public Collection<DistrictChannelStructure> getDistChannelStruct() {
		return distChannelStruct;
	}
	public void setDistChannelStruct(
			Collection<DistrictChannelStructure> distChannelStruct) {
		this.distChannelStruct = distChannelStruct;
	}
	public Collection<DistrictChannelIntermediary> getDistChannelIntermediary() {
		return distChannelIntermediary;
	}
	public void setDistChannelIntermediary(
			Collection<DistrictChannelIntermediary> distChannelIntermediary) {
		this.distChannelIntermediary = distChannelIntermediary;
	}
	@OneToMany(cascade= CascadeType.ALL, mappedBy="createdBy")
	private Collection<DistrictChannelStructure> distChannelStruct;
	
	public Collection<DistrictChannel> getDistChannel() {
		return distChannel;
	}
	public void setDistChannel(Collection<DistrictChannel> distChannel) {
		this.distChannel = distChannel;
	}*/
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;

	@Column(name="CREATED_ON")
	private Date createdOn;
	
	/*@OneToMany(cascade= CascadeType.ALL, mappedBy="createdBy")
	private Collection<DistrictChannelIntermediary> distChannelIntermediary;*/
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="CREATED_BY")
	private Collection<Zone> zones;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Collection<Org> organizations;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Collection<SalesForOrgMaster> sfordids;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Collection<SalesForOrgSpecialization> splId;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Collection<SalesForOrgMasterInter> sforgInterId;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private Collection<SalesForOrgStruc> sforgstrucId;
	
	
	public Collection<SalesForOrgSpecialization> getSplId() {
		return splId;
	}
	public void setSplId(Collection<SalesForOrgSpecialization> splId) {
		this.splId = splId;
	}
	public Collection<SalesForOrgStruc> getSforgstrucId() {
		return sforgstrucId;
	}
	public void setSforgstrucId(Collection<SalesForOrgStruc> sforgstrucId) {
		this.sforgstrucId = sforgstrucId;
	}
	public Collection<SalesForOrgMasterInter> getSforgInterId() {
		return sforgInterId;
	}
	public void setSforgInterId(Collection<SalesForOrgMasterInter> sforgInterId) {
		this.sforgInterId = sforgInterId;
	}
	public Collection<SalesForOrgMaster> getSfordids() {
		return sfordids;
	}
	public void setSfordids(Collection<SalesForOrgMaster> sfordids) {
		this.sfordids = sfordids;
	}
	
	public Collection<Org> getOrganizations() {
		return organizations;
	}
	public void setOrganizations(Collection<Org> organizations) {
		this.organizations = organizations;
	}
	public Collection<Zone> getZones() {
		return zones;
	}
	public void setZones(Collection<Zone> zones) {
		this.zones = zones;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

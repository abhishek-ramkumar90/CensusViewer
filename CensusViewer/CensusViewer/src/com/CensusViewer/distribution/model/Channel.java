package com.mars.distribution.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CHANNEL", schema="distribution")
public class Channel {
	@Id
	@Column(name="CHANNEL_ID")
	private String channelId;
	
	@Column(name="CHANNEL_NAME")
	private String channelName;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinTable(name="CHANNEL_INTERMEDIARY",schema="distribution")
	private List<Intermediary> intermCollection=new ArrayList<Intermediary>();
	
	@Column(name="LEVEL_COUNT")
	private int levelCount;

	@Column(name="STATUS")
	private int status;
	
	@Column(name="ORG_ID")
	private String orgId;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="MODIFIED_ON")
	private Date modifiedOn;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date date) {
		this.createdOn = date;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public List<Intermediary> getIntermCollection() {
		return intermCollection;
	}

	public void setIntermCollection(List<Intermediary> intermCollection) {
		this.intermCollection = intermCollection;
	}

}

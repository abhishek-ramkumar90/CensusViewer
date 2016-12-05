package com.mars.distribution.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(name="BRANCH_MASTER", schema = "distribution")
public class Branch {

	@Id
	//@GenericGenerator(name="seq_id", strategy="com.mars.distribution.id.generator.BranchIdGenerator" )
	//@GeneratedValue(generator="seq_id")
	@Column(name = "BRANCH_ID", unique = true, nullable = false, length = 20)
	private String branchId;

	@Column(name="BRANCH_NAME")
	private String branch;

	@Column(name="CREATED_ON")
	private Date createdOn;


	@Column(name="gid")
	private long gid;
	@Column(name="STATUS")
	private int status;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="BRANCH_ID")
	private Collection<BrDistrict> districts;

	
	/*@OneToMany(cascade= CascadeType.ALL,mappedBy="branchId")
	private Collection<DistrictChannelIntermediaryDetail> distChannelIntermDetail;

	public Collection<DistrictChannelIntermediaryDetail> getDistChannelIntermDetail() {
		return distChannelIntermDetail;
	}

	public void setDistChannelIntermDetail(
			Collection<DistrictChannelIntermediaryDetail> distChannelIntermDetail) {
		this.distChannelIntermDetail = distChannelIntermDetail;
	}
*/
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="BRANCH_ID")
	private Collection<Territory> territories;

	public Collection<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(Collection<Territory> territories) {
		this.territories = territories;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

		public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
	}

		public Collection<BrDistrict> getDistricts() {
		return districts;
	}

	public void setDistricts(Collection<BrDistrict> districts) {
		this.districts = districts;
	}



	
}

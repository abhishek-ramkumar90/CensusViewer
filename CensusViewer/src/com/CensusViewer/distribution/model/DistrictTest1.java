package com.CensusViewer.distribution.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;
import org.postgis.Geometry;

@Entity
@Table(name="district_test1",schema = "distribution")
public class DistrictTest1 {

	
	@Column(name="the_geom")
	@Type(type="org.hibernate.spatial.GeometryType")
	private Geometry geomData;
	@Column(name="name")
	private String name;
	@Id
	@Column(name="branch_id")
	private String branchId;
	@Column(name="gid")
	private long gid;
	@Column(name="status")
	private int status;

	@XmlTransient
	public Geometry getGeomData() {
		return geomData;
	}
	public void setGeomData(Geometry geomData) {
		this.geomData = geomData;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}

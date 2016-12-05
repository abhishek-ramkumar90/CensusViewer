package com.mars.distribution.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Type;
import org.postgis.Geometry;
@Entity
@Table(name="region_geom",schema = "distribution")
public class RegionGeometry {


	@Column(name="the_geom")
	@Type(type="org.hibernate.spatial.GeometryType")
	private Geometry geomData;
	@Column(name="name")
	private String name;
	@Id
	@Column(name="region_id")
	private String regionId;
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
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	


}


package com.mars.census.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="village")
@Table(name="village")

public class VillageTable {
	@Id
	@Column(name="villid")
	private Integer villid;
	@Column(name="name")
	private String name;
	
	@Column(name="distid")
	private Integer distid;
	@Column(name="distcode")
	private String distCode;

	
	

	public String getDistCode() {
		return distCode;
	}
	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}
	public Integer getDistid() {
		return distid;
	}
	public void setDistid(Integer distid) {
		this.distid = distid;
	}
	public Integer getVillid() {
		return villid;
	}
	public void setVillid(Integer villid) {
		this.villid = villid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
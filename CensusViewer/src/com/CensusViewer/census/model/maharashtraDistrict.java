package com.CensusViewer.census.model;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
@Entity
@Table(name ="district")


public class maharashtraDistrict {
	@Id
	@Column(name="distid")
	private Integer distid;
	
		public Integer getDistid() {
		return distid;
	}

	public void setDistid(Integer distid) {
		this.distid = distid;
	}

		@Column(name = "gid")
	private BigDecimal gid ;

	@Column(name = "statecode")
	private String  statecode ;
	@Column(name = "distcode")
	private String dist_code ;



	@Column(name = "level")
	private String level ;

	@Column(name = "name")
	private String name;

	public BigDecimal getGid() {
		return gid;
	}

	public void setGid(BigDecimal gid) {
		this.gid = gid;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getDist_code() {
		return dist_code;
	}

	public void setDist_code(String dist_code) {
		this.dist_code = dist_code;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
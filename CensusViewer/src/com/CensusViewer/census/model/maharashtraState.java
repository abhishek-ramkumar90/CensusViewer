package com.CensusViewer.census.model;
import java.math.BigDecimal;
import java.lang.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;
@Entity
@Table(name = "state")


public class maharashtraState {
	@Id
	@Column(name = "stateid")
	private Integer		  stateid;
	@Column(name = "gid")
	private BigDecimal	gid;
	@Column(name = " statecode")
	private String	  statecode;
	
	@Column(name = " name ")
	private String		  name ;

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
//private BigDecimal		  the_geom; 
//private BigDecimal		  stateid; 
	
}

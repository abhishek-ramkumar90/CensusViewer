package com.mars.census.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name="country_master")
@Table(name="country_master")
public class Country {
	
	private BigDecimal   gid ;
	private String   c_code ;
	private String    c_name ;
	@Id
	@Column(name = "gid")
	public BigDecimal getGid() {
		return gid;
	}
	public void setGid(BigDecimal gid) {
		this.gid = gid;
	}
	
	@Column(name = "c_code")
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	
	@Column(name = "c_name")
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
}

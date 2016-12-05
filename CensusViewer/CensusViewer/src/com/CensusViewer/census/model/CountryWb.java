package com.mars.census.model;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Country")
public class CountryWb {
	private BigDecimal   gid ;
	private String   c_code ;
	private String    c_name ;
	private List<CountryWb> country;
	public List<CountryWb> getCountry() {
		return country;
	}
	public void setCountry(List<CountryWb> country) {
		this.country = country;
	}
	public BigDecimal getGid() {
		return gid;
	}
	public void setGid(BigDecimal gid) {
		this.gid = gid;
	}
	public String getC_code() {
		return c_code;
	}
	public void setC_code(String c_code) {
		this.c_code = c_code;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
}

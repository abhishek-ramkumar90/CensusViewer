package com.mars.census.model;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "Town")
public class TownWb {
	private List<TownWb> town;

	private BigDecimal gid ;
	private String  statecode;
	private String  tehsilcode;
	private String town_name;

	public List<TownWb> getTown() {
		return town;
	}

	public void setTown(List<TownWb> town) {
		this.town =town;
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

	public String getTehsilcode() {
		return tehsilcode;
	}

	public void setTehsilcode(String tehsilcode) {
		this.tehsilcode = tehsilcode;
	}
	public String getTown_name() {
		return town_name;
	}
	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}
}
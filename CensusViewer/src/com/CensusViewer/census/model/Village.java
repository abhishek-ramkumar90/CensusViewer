package com.CensusViewer.census.model;

import java.util.Collection;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Village")
public class Village {
	
	private Integer villid;
	private String villageName;
	private Integer distid;
	private String distCode;
	private String segment;
    private String tehsil;
    private String districtName;
    private String stateName;
    private String tehsilCode;
    private String statecode;
	
	public Integer getVillid() {
		return villid;
	}

	public void setVillid(Integer villid) {
		this.villid = villid;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Integer getDistid() {
		return distid;
	}

	public void setDistid(Integer distid) {
		this.distid = distid;
	}

	public String getDistCode() {
		return distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getTehsilCode() {
		return tehsilCode;
	}

	public void setTehsilCode(String tehsilCode) {
		this.tehsilCode = tehsilCode;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	private Collection<Village> villages;
	public Collection<Village> getVillages() {
		return villages;
	}
	
	public void setVillages(Collection<Village> villages) {
		// TODO Auto-generated method stub
		this.villages = villages;
	}
}
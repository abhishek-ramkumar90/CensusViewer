package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RegionList")
public class RegionListJson {
	
	private String RegionId;
	
	private String RegionName;
	
	public Collection<RegionListJson> getRegionList() {
		return RegionList;
	}
	public void setRegionList(Collection<RegionListJson> regionList) {
		RegionList = regionList;
	}
	private Collection<RegionListJson>RegionList;
	
	public String getRegionId() {
		return RegionId;
	}
	public void setRegionId(String regionId) {
		RegionId = regionId;
	}

	
	public String getRegionName() {
		return RegionName;
	}
	public void setRegionName(String regionName) {
		RegionName = regionName;
	}

}

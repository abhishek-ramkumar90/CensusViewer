package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="RegionListBr")
public class RegionListBranch implements Comparator<RegionListBranch>{

	private String regionId;
	private String regionName;
	private String zoneId;
	private Collection<RegionListBranch> regionlist;
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public Collection<RegionListBranch> getRegionlist() {
		return regionlist;
	}
	public void setRegionlist(Collection<RegionListBranch> regionlist) {
		this.regionlist = regionlist;
	}
	@Override
	public int compare(RegionListBranch r1, RegionListBranch r2) {
		if(r1.getRegionName().equals(r2.getRegionName()))
		{
			return r1.getRegionName().compareTo(r2.getRegionName());
		}
		return r1.getRegionName().compareTo(r2.getRegionName());



	}
	
}

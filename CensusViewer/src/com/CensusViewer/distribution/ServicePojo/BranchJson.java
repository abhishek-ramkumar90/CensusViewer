package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.CensusViewer.distribution.model.BrDistrict;
@XmlRootElement(name="BranchDetails")
public class BranchJson {

	private String branchName;
	private String branchId;
	private String regionName;
	private String regionId; 
	private String zoneName;
	private String zoneId;
	private Collection<BrDistrict> brDistricts;
	
	
	public Collection<BrDistrict> getBrDistricts() {
		return brDistricts;
	}
	public void setBrDistricts(Collection<BrDistrict> brDistricts) {
		this.brDistricts = brDistricts;
	}
	private Collection<BranchJson> branchJsonList;
	
	
	public Collection<BranchJson> getBranchJsonList() {
		return branchJsonList;
	}
	public void setBranchJsonList(Collection<BranchJson> branchJsonList) {
		this.branchJsonList = branchJsonList;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
	
}

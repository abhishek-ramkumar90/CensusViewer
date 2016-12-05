package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="TerritoryDetails")
public class TerritoryJson {

	private String territoryName;
	private String territoyId;
	private String branchName;
	private String branchId;
	private String regionName;
	private String regionId; 
	private String zoneName;
	private String zoneId;
	private Collection<TerritoryJson> territoryJsonList;
	private Collection<TerrVillTownJson> stateDistVillTown;

	
	public Collection<TerrVillTownJson> getStateDistVillTown() {
		return stateDistVillTown;
	}
	public void setStateDistVillTown(Collection<TerrVillTownJson> stateDistVillTown) {
		this.stateDistVillTown = stateDistVillTown;
	}
	public String getTerritoryName() {
		return territoryName;
	}
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}
	public String getTerritoyId() {
		return territoyId;
	}
	public void setTerritoyId(String territoyId) {
		this.territoyId = territoyId;
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
	public Collection<TerritoryJson> getTerritoryJsonList() {
		return territoryJsonList;
	}
	public void setTerritoryJsonList(Collection<TerritoryJson> territoryJsonList) {
		this.territoryJsonList = territoryJsonList;
	}
	
}

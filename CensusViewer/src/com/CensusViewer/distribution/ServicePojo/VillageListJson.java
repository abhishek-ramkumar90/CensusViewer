package com.CensusViewer.distribution.ServicePojo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="VillageList")
public class VillageListJson implements Comparator<VillageListJson> {

	private String villageId;
	private String villageName;
	private String distId;
	private String distName;
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	private String stateCode;
	private Collection<VillageListJson> villageList;
	private Double lat;
	private Double longitude;
	private int gid;
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}

	public Collection<VillageListJson> getVillageList() {
		return villageList;
	}
	public void setVillageList(Collection<VillageListJson> villageList) {
		this.villageList = villageList;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	@Override
	public int compare(VillageListJson v1, VillageListJson v2) {
		if(v1.getVillageName().equals(v2.getVillageName()))
		{
			return v1.getVillageName().compareTo(v2.getVillageName());
		}
		return v1.getVillageName().compareTo(v2.getVillageName());

	}
	
}

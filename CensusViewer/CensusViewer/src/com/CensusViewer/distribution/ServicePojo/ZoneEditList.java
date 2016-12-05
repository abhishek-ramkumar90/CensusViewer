package com.mars.distribution.ServicePojo;

import java.util.Collection;

public class ZoneEditList {

	private String stateId;
	private String stateName;
	private String zoneId;
	private Collection<ZoneEditList> zoneList;
	public Collection<ZoneEditList> getZoneList() {
		return zoneList;
	}
	public void setZoneList(Collection<ZoneEditList> zoneList) {
		this.zoneList = zoneList;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
}

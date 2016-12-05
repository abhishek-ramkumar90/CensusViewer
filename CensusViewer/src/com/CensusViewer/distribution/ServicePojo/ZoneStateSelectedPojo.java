package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.CensusViewer.distribution.model.ZoneStates;

@XmlRootElement(name="SelectedStateList")
public class ZoneStateSelectedPojo {

private String Zoneid;
private String ZoneName;

public String getZoneName() {
	return ZoneName;
}
public void setZoneName(String zoneName) {
	ZoneName = zoneName;
}

public Collection<ZoneStates> getZoneStateList() {
	return ZoneStateList;
}
public void setZoneStateList(Collection<ZoneStates> zoneStateList) {
	ZoneStateList = zoneStateList;
}

private String stateId;

private Collection<ZoneStates> ZoneStateList;

public String getZoneid() {
	return Zoneid;
}
public void setZoneid(String zoneid) {
	Zoneid = zoneid;
}
public String getStateId() {
	return stateId;
}
public void setStateId(String stateId) {
	this.stateId = stateId;
}
}

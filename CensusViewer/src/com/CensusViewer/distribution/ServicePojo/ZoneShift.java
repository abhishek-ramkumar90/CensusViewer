package com.CensusViewer.distribution.ServicePojo;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ZoneShift")
public class ZoneShift {
private String zoneName;
public String getZoneId() {
	return zoneId;
}
public void setZoneId(String zoneId) {
	this.zoneId = zoneId;
}
public String getStateId() {
	return stateId;
}
public void setStateId(String stateId) {
	this.stateId = stateId;
}
private String zoneId;
private String stateId;
private String stateName;
private Collection<ZoneShift> shiftCollection;
public String getZoneName() {
	return zoneName;
}
public void setZoneName(String zoneName) {
	this.zoneName = zoneName;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
public Collection<ZoneShift> getShiftCollection() {
	return shiftCollection;
}
public void setShiftCollection(Collection<ZoneShift> shiftCollection) {
	this.shiftCollection = shiftCollection;
}
	
}

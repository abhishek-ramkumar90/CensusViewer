package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.CensusViewer.distribution.model.ZoneStateListFinal;
@XmlRootElement(name=" ZoneList")
public class ZoneCreationPojo {
private String ZoneName;
private String ZoneId;

public String getZoneName() {
	return ZoneName;
}

public void setZoneName(String zoneName) {
	ZoneName = zoneName;
}

public String getZoneId() {
	return ZoneId;
}

public void setZoneId(String zoneId) {
	ZoneId = zoneId;
}

private Collection<ZoneStateListFinal> stateCollection;

public Collection<ZoneStateListFinal> getStateCollection() {
	return stateCollection;
}

public void setStateCollection(Collection<ZoneStateListFinal> stateCollection) {
	this.stateCollection = stateCollection;
}
	 
}

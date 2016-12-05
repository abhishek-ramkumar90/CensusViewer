package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/*@XmlType(name = "ZoneList")*/
@XmlRootElement(name="ZoneList")
public class ZoneListJson implements Comparator<ZoneListJson> {
	private String zoneId;
	private String zoneName;
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
	
	public Collection<ZoneListJson> getZoneList() {
		return zoneList;
	}
	public void setZoneList(Collection<ZoneListJson> zoneList) {
		this.zoneList = zoneList;
	}
	private Collection<ZoneListJson> zoneList;
	
/*	@Override
	public int compare(BranchListTerrJson b1, BranchListTerrJson b2) {

	if(b1.getBranchName().equals(b2.getBranchName()))
	{
	return b1.getBranchName().compareTo(b2.getBranchName());
	}
	return b1.getBranchName().compareTo(b2.getBranchName());


	}*/
/*	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}*/
	
@Override
public int compare(ZoneListJson o1, ZoneListJson o2) {
	// TODO Auto-generated method stub
	if(o1.getZoneName().equals(o2.getZoneName()))
	{
	return o1.getZoneName().compareTo(o2.getZoneName());
	}
	return o1.getZoneName().compareTo(o2.getZoneName());
}
	
}

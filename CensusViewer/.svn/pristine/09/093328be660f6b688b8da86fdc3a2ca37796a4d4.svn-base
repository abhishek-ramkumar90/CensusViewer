package com.mars.distribution.ServicePojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.mars.distribution.model.Region;
import com.mars.distribution.model.ZoneStateListFinal;
@XmlRootElement(name = "ZoneRegionList")
public class ZoneRegionListJson implements Comparator<ZoneRegionListJson> {
	
    private String ZoneId;
	
    private String ZoneName;

	public String getZoneId() {
		return ZoneId;
	}

	public void setZoneId(String zoneId) {
		ZoneId = zoneId;
	}

	public String getZoneName() {
		return ZoneName;
	}

	public void setZoneName(String zoneName) {
		ZoneName = zoneName;
	}

	private Collection<ZoneStateListFinal>  stateDetails=new ArrayList<ZoneStateListFinal>(); 
	
	/*@XmlTransient
	public Collection<Region> getStateDetails1() {
		return stateDetails1;
	}

	public void setStateDetails1(Collection<Region> stateDetails1) {
		this.stateDetails1 = stateDetails1;
	}

	private Collection<Region>  stateDetails1=new ArrayList<Region>();
	*/
	public Collection<ZoneStateListFinal> getStateDetails() {
		return stateDetails;
	}

	public void setStateDetails(Collection<ZoneStateListFinal> stateDetails) {
		this.stateDetails = stateDetails;
	}

	private Collection<ZoneRegionListJson> ZoneRegionList;

	public Collection<ZoneRegionListJson> getZoneRegionList() {
		return ZoneRegionList;
	}

	public void setZoneRegionList(Collection<ZoneRegionListJson> zoneRegionList) {
		ZoneRegionList = zoneRegionList;
	}

	@Override
	public int compare(ZoneRegionListJson o1, ZoneRegionListJson o2) {
		// TODO Auto-generated method stub
		if(o1.getZoneName().equals(o2.getZoneName()))
		{
		return o1.getZoneName().compareTo(o2.getZoneName());
		}
		return o1.getZoneName().compareTo(o2.getZoneName());
	}



}

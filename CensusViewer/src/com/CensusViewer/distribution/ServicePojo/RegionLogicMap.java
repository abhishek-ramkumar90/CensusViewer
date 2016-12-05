package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RegionLogicMap")
public class RegionLogicMap {
	private String distIds;
	
	private Collection<String> distIdCollection;

	public String getDistIds() {
		return distIds;
	}

	public void setDistIds(String distIds) {
		this.distIds = distIds;
	}

	public Collection<String> getDistIdCollection() {
		return distIdCollection;
	}

	public void setDistIdCollection(Collection<String> distIdCollection) {
		this.distIdCollection = distIdCollection;
	} 

}

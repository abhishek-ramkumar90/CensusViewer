package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Ids")

public class RegionDistIds {

	private String DistIds;
private Collection<RegionDistIds> distids;
	public Collection<RegionDistIds> getDistids() {
	return distids;
}

public void setDistids(Collection<RegionDistIds> distids) {
	this.distids = distids;
}

	public String getDistIds() {
		return DistIds;
	}

	public void setDistIds(String distIds) {
		DistIds = distIds;
	}
}

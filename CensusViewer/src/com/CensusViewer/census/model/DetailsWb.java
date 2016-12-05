package com.CensusViewer.census.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Details")
public class DetailsWb {
	private Collection<maharashtraDistrict1> distDetails;

	public Collection<maharashtraDistrict1> getDistDetails() {
		return distDetails;
	}

	public void setDistDetails(Collection<maharashtraDistrict1> distDetails) {
		this.distDetails = distDetails;
	}

	
}

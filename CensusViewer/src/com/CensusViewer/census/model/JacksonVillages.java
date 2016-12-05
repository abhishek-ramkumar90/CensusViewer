package com.CensusViewer.census.model;

import java.math.BigDecimal;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "JacksonVillages")
public class JacksonVillages implements java.io.Serializable {

	private Collection<VillageTable> jacksonVillageDetails;

	public Collection<VillageTable> getJacksonVillageDetails() {
		return jacksonVillageDetails;
	}

	public void setJacksonVillageDetails(
			Collection<VillageTable> jacksonVillageDetails) {
		this.jacksonVillageDetails = jacksonVillageDetails;
	}
}
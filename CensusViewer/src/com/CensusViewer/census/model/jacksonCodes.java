package com.CensusViewer.census.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "JacksonState")
public class jacksonCodes implements java.io.Serializable{

	private Collection<maharashtraState> jacksonStateDetails;

	public Collection<maharashtraState> getJacksonStateDetails() {
		return jacksonStateDetails;
	}

	public void setJacksonStateDetails(
			Collection<maharashtraState> jacksonStateDetails) {
		this.jacksonStateDetails = jacksonStateDetails;
	}
}

package com.mars.distribution.ServicePojo;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RegionStateList")

public class RegionStateListJson {
	private String state_id;
	
	private String state_name;
	
	private Collection<RegionStateListJson> StateDetails;
	
	public String getState_id() {
		return state_id;
	}
	public void setState_id(String state_id) {
		this.state_id = state_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public Collection<RegionStateListJson> getStateDetails() {
		return StateDetails;
	}
	public void setStateDetails(Collection<RegionStateListJson> stateDetails) {
		StateDetails = stateDetails;
	}

}

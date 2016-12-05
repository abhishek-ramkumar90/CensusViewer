package com.CensusViewer.distribution.ServicePojo;

import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="DistrictList")
public class RegDistJson implements Comparator<RegDistJson> {
	private String regId;
	private int distCode;
	private String distName;
	private String stateCode;
	private int gid;
	private Collection<RegDistJson> dists;
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	
	public int getDistCode() {
		return distCode;
	}
	public void setDistCode(int distCode) {
		this.distCode = distCode;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public Collection<RegDistJson> getDists() {
		return dists;
	}
	public void setDists(Collection<RegDistJson> dists) {
		this.dists = dists;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	@Override
	public int compare(RegDistJson d1, RegDistJson d2) {
		if(d1.getDistName().equals(d2.getDistName()))
		{
			return d1.getDistName().compareTo(d2.getDistName());
		}
		return d1.getDistName().compareTo(d2.getDistName());

	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	

}

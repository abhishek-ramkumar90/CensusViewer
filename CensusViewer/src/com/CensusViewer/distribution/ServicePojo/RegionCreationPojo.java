package com.CensusViewer.distribution.ServicePojo;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import com.CensusViewer.distribution.model.Region;
@XmlRootElement(name="RegionList")
public class RegionCreationPojo {
	private String zoneName;
	 private Collection<Region> regions;
	 private Collection<RegionCreationPojo> regionpojo;
	 private RegionCreationPojo regionPojo;
	public RegionCreationPojo getRegionPojo() {
		return regionPojo;
	}
	public void setRegionPojo(RegionCreationPojo regionPojo) {
		this.regionPojo = regionPojo;
	}
	public Collection<RegionCreationPojo> getRegionpojo() {
		return regionpojo;
	}
	public void setRegionpojo(Collection<RegionCreationPojo> regionpojo) {
		this.regionpojo = regionpojo;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public Collection<Region> getRegions() {
		return regions;
	}
	public void setRegions(Collection<Region> regions) {
		this.regions = regions;
	}

}
	


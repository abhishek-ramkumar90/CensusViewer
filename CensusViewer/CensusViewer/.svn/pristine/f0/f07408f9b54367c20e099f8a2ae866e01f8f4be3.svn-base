package com.mars.distribution.ServicePojo;

import java.util.Collection;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TerrVillTownDetails")
public class TerrVillTownJson implements Comparator<TerrVillTownJson>  {
	private String statecode;
	private String distCode;
	private int villTownCode;
	private String level;
	private String villTownName;
	private Collection<TerrVillTownJson> terVillTown;
	public String getStatecode() {
		return statecode;
	}
	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}
	public String getDistCode() {
		return distCode;
	}
	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}
	
	public int getVillTownCode() {
		return villTownCode;
	}
	public void setVillTownCode(int villTownCode) {
		this.villTownCode = villTownCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Collection<TerrVillTownJson> getTerVillTown() {
		return terVillTown;
	}
	public void setTerVillTown(Collection<TerrVillTownJson> terVillTown) {
		this.terVillTown = terVillTown;
	}
	public String getVillTownName() {
		return villTownName;
	}
	public void setVillTownName(String villTownName) {
		this.villTownName = villTownName;
	}
	

	@Override
	public int compare(TerrVillTownJson t1, TerrVillTownJson t2) {
		if(t1.getVillTownName().equals(t2.getVillTownName())) {
			return t1.getVillTownName().compareTo(t2.getVillTownName());
		}
		return t1.getVillTownName().compareTo(t2.getVillTownName());

	}
}
